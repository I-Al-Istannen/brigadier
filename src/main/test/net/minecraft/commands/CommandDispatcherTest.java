package net.minecraft.commands;

import net.minecraft.commands.exceptions.UnknownCommandException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandDispatcherTest {
    CommandDispatcher subject;
    @Mock Runnable runnable;

    @Before
    public void setUp() throws Exception {
        subject = new CommandDispatcher();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateCommand() throws Exception {
        subject.createCommand("foo").executes(runnable).finish();
        subject.createCommand("foo").executes(runnable).finish();
    }

    @Test
    public void testCreateAndExecuteCommand() throws Exception {
        subject.createCommand("foo").executes(runnable).finish();

        subject.execute("foo");
        Mockito.verify(runnable).run();
    }

    @Test(expected = UnknownCommandException.class)
    public void testExecuteUnknownCommand() throws Exception {
        subject.execute("foo");
    }
}