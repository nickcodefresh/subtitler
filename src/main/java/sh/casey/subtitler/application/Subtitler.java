package sh.casey.subtitler.application;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;
import sh.casey.subtitler.application.command.AlignCommand;
import sh.casey.subtitler.application.command.ShiftCommand;

import java.util.concurrent.Callable;

@Command(
    name = "subtitler",
    version = "2.0",
    description = "Subtitler is a command line tool for manipulating subtitle files.",
    mixinStandardHelpOptions = true,
    subcommands = {
        AlignCommand.class,
        ShiftCommand.class,
    }
)
public class Subtitler implements Callable<Integer> {

    @Spec
    private CommandSpec spec;

    public static void main(String[] args) {
        final int exitCode = new CommandLine(new Subtitler())
            .setCaseInsensitiveEnumValuesAllowed(true)
            .setUsageHelpAutoWidth(true)
            .setUsageHelpLongOptionsMaxWidth(25)
            .execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        // Print the help command if no parameters are specified
        spec.commandLine().usage(System.err);
        return 1;
    }
}
