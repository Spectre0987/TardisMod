package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;
import net.tardis.mod.util.common.helpers.FileHelper;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.*;


public class CommandTardis extends CommandBase {

	public static final List<String> subcommands = Arrays.asList("grow", "transfer", "interior", "remove", "restoresys");

	/**
	 * Gets the name of the command
	 */
	@Override
	public String getName() {
		return "tardis";
	}

	/**
	 * Gets the usage string for the command.
	 *
	 * @param sender
	 */
	@Override
	public String getUsage(ICommandSender sender) {
		StringBuilder usageString = new StringBuilder();
		usageString.append("/tardis ");
		for (String subcommand : subcommands) {
			usageString.append(MessageFormat.format("{0} | ", subcommand));
		}
		return usageString.toString();
	}

	/**
	 * Callback for when the command is executed
	 *
	 * @param server
	 * @param sender
	 * @param args
	 */
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (!(sender instanceof EntityPlayerMP))
			throw new CommandException("You are not a player. You must run these commands in game.");

		if (args.length == 0 || !subcommands.contains(args[0]))
			throw new CommandException("You need to include a argument of the following: " + this.getUsage(sender));

		EntityPlayerMP player = getCommandSenderAsPlayer(sender);

		switch (subcommands.indexOf(args[0])) {
			case 0: //grow
				if (PermissionAPI.hasPermission(player,TStrings.Permissions.GROW))
					handleGrow(player);
				else
					throw new CommandException("You do not have permission to run this command.");
				break;

			case 1: //transfer
				if (args.length == 2)
					handlerOwner(player, args[1]);
				else
					throw new CommandException("/tardis transfer <username>");
				break;

			case 2: //interior
				if (args.length > 1 && PermissionAPI.hasPermission(player,TStrings.Permissions.TP_IN_TARDIS_OTHER)){
					handleTeleport(player,args[1]);
				}
				else if (PermissionAPI.hasPermission(player, TStrings.Permissions.TP_IN_TARDIS)){
					handleTeleport(player, null);
				}
				else
					throw new CommandException("You do not have permission to run this command or you do it wrong.");
				break;
			case 3: //remove
				if (args.length == 2) {
					if (PermissionAPI.hasPermission(player, TStrings.Permissions.REMOVE_TARDIS))
						handleRemove(player, args[1]);
					else
						throw new CommandException("You do not have permission to run this command.");
				} else
					throw new CommandException("/tardis remove <username>");
				break;

			case 4: //restoresys
				if (args.length > 1) {
					if (PermissionAPI.hasPermission(player, TStrings.Permissions.RESTORE_TARDIS)) {
						if (Arrays.asList(server.getOnlinePlayerNames()).contains(args[1]))
							player = server.getPlayerList().getPlayerByUsername(args[1]);

						List<String> systemNames = new ArrayList<>();
						for (int i = 1; i < args.length; i++)
							systemNames.add(args[i]);

						this.restoreSystem(player, player.getUniqueID(), systemNames.toArray(new String[]{}));
					} else
						throw new CommandException("You do not have permission to run this command.");
				} else
					throw new CommandException("/tardis restoresys <username> <system1> [system2] [system3]...");
				break;
		}
	}

	//handle restoring systems
	private void restoreSystem(EntityPlayerMP player, @Nullable UUID id, String... names) throws CommandException {
		List<BaseSystem> systemBases = new ArrayList<>();
		UUID owner = (id == null ? player.getUniqueID() : id);

		for (String s : names) {
			BaseSystem system = TardisSystems.createFromName(s);
			if (system != null)
				systemBases.add(system);
			else
				player.sendMessage(new TextComponentString(s + ": " + new TextComponentTranslation(TStrings.Commands.NO_SYSTEM).getFormattedText()));
		}

		if (TardisHelper.hasTardis(owner)) {
			TileEntityTardis tardis = (TileEntityTardis) player.getServerWorld().getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(TardisHelper.getTardis(owner));
			if (tardis != null) {
				for (BaseSystem system : systemBases) {
					tardis.getSystem(system.getClass()).setHealth(1F);
				}
				player.sendStatusMessage(new TextComponentTranslation(TStrings.Commands.SYSTEM_RESTORED), false);
			} else throw new CommandException(TStrings.Commands.NO_TARIDS_IN_WORLD);
		} else throw new CommandException(TStrings.Commands.NO_TARDIS_OWNED);

	}

	//Handle removing the Tardis
	private void handleRemove(EntityPlayerMP senderPlayer, String toBeRemoved) {
		MinecraftServer server = senderPlayer.getServer();
		Map<UUID, String> playersMap = FileHelper.getPlayersFromServerFile();

		if (playersMap.containsValue(toBeRemoved)) {
			UUID toBeRemovedID = Helper.getKeyByValue(playersMap, toBeRemoved);
			if (TardisHelper.hasTardis(toBeRemovedID)) {
				server.getWorld(TDimensions.TARDIS_ID).setBlockToAir(TardisHelper.getTardis(toBeRemovedID));
				TardisHelper.tardisOwners.remove(toBeRemovedID.toString());
				senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.TARDIS_DELETED));
			} else {
				senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
			}
		} else {
			senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
		}
	}

	//Handles transfering Tardis ownership
	private void handlerOwner(EntityPlayerMP player, String newOwnerName) {
		MinecraftServer server = player.getServer();
		EntityPlayer newOwner = server.getPlayerList().getPlayerByUsername(newOwnerName);
		if (newOwner != null && TardisHelper.hasTardis(player.getGameProfile().getId()) && !TardisHelper.hasTardis(newOwner.getGameProfile().getId())) {
			BlockPos pos = TardisHelper.getTardis(player.getGameProfile().getId());
			if (pos != null) {
				TardisHelper.tardisOwners.remove(player.getGameProfile().getId().toString());
				TardisHelper.tardisOwners.put(newOwner.getGameProfile().getId().toString(), pos.toImmutable());
			}
		}
	}

	//Grow the tardis
	private void handleGrow(EntityPlayerMP player) {
		World world = player.getEntityWorld();
		RayTraceResult ray = world.rayTraceBlocks(player.getPositionVector().add(0, player.getEyeHeight(), 0), player.getPositionVector().add(player.getLookVec().scale(16D)));
		if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
			TileEntity te = world.getTileEntity(ray.getBlockPos());
			if (te != null && te instanceof TileEntityTardisCoral) {
				((TileEntityTardisCoral) te).grow();
			}
		}
	}

	//Handles teleporting the user to their interior
	private void handleTeleport(EntityPlayerMP player, @Nullable String tardisOwner) {
		MinecraftServer server = player.getServer();
		UUID playerID;

		if (tardisOwner == null){
			playerID = player.getUniqueID();
		}
		else {
			Map<UUID, String> playersMap = FileHelper.getPlayersFromServerFile();
			playerID = Helper.getKeyByValue(playersMap, tardisOwner);
		}

		if (TardisHelper.hasTardis(playerID)) {
			BlockPos pos = TardisHelper.getTardis(playerID);
			player.dismountRidingEntity();
			TileEntityTardis tileTardis = TardisHelper.getConsole(pos);
			if (tileTardis != null) {
				tileTardis.enterTARDIS(player);
			} else {
				player.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED + " but most likely a issue has arisen somewhere..."));
			}
		} else {
			player.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
		}
	}

	/**
	 * Get a list of options for when the user presses the TAB key
	 */
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if (args.length < 2) {
			return getListOfStringsMatchingLastWord(args, subcommands);
		}

		if ((args[0].equals("interior") || args[0].equals("remove")) && FMLCommonHandler.instance().getSide() == Side.SERVER) {
			return getListOfStringsMatchingLastWord(args, FileHelper.getPlayersFromServerFile().values());
		}

		if (args[0].equals("transfer")) {
			return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
		}

		if (args[0].equals("restoresys")) {
			List<String> systemNames = new ArrayList<String>();

			for (Map.Entry<String, Class<? extends BaseSystem>> entry : TardisSystems.SYSTEMS.entrySet()) {
				systemNames.add(entry.getKey());
			}

			if (args.length == 2) {
				systemNames.addAll(Arrays.asList(server.getOnlinePlayerNames()));
			}
			return getListOfStringsMatchingLastWord(args, systemNames);
		}
		return Collections.emptyList();
	}
}
