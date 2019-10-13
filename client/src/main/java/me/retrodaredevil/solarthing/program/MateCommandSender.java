package me.retrodaredevil.solarthing.program;

import me.retrodaredevil.solarthing.OnDataReceive;
import me.retrodaredevil.solarthing.commands.CommandProvider;
import me.retrodaredevil.solarthing.commands.OnCommandExecute;
import me.retrodaredevil.solarthing.commands.SourcedCommand;
import me.retrodaredevil.solarthing.solar.outback.command.MateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class MateCommandSender implements OnDataReceive {
	private static final Logger LOGGER = LoggerFactory.getLogger(MateCommandSender.class);
	private final CommandProvider<MateCommand> commandProvider;
	private final OutputStream outputStream;
	private final Collection<MateCommand> allowedCommands;
	private final OnCommandExecute<MateCommand> onCommandExecute;
	
	public MateCommandSender(CommandProvider<MateCommand> commandProvider, OutputStream outputStream, Collection<MateCommand> allowedCommands, OnCommandExecute<MateCommand> onCommandExecute) {
		this.commandProvider = requireNonNull(commandProvider);
		this.outputStream = requireNonNull(outputStream);
		this.allowedCommands = requireNonNull(allowedCommands);
		this.onCommandExecute = requireNonNull(onCommandExecute);
	}
	
	public void onDataReceive(boolean firstData, boolean wasInstant) {
		if(firstData && wasInstant){
			SourcedCommand<MateCommand> sourcedCommand = commandProvider.pollCommand();
			if(sourcedCommand != null){
				MateCommand command = sourcedCommand.getCommand();
				if(!allowedCommands.contains(command)){
					LOGGER.warn("Command: " + command + " is not allowed!");
					return;
				}
				try {
					command.send(outputStream);
				} catch (IOException e) {
					throw new RuntimeException("Unable to send command: " + command, e);
				}
				LOGGER.info("\nSent command: " + command + " at " + System.currentTimeMillis());
				onCommandExecute.onCommandExecute(sourcedCommand);
			}
		}
	}
}
