package com.infoshareacademy.dreamteam.concurrent;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.concurrent.Executor;

@Stateless
@Default
public class TransactionalExecutor implements Executor {

    @Override
    @Asynchronous
    public void execute(Runnable command) {
        command.run();
    }
}
