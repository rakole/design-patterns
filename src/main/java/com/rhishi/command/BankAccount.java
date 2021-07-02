package com.rhishi.command;

import java.util.Arrays;
import java.util.List;

import static com.rhishi.command.Commands.DEPOSIT;
import static com.rhishi.command.Commands.WITHDRAW;
import static java.util.Arrays.asList;
import static java.util.List.*;

enum Commands {
    WITHDRAW, DEPOSIT;
}

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public boolean deposit(int amount) {
        System.out.println("depositing money to bank account!");
        balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if ((balance - amount) < 0) {
            System.out.println("withdrawal failed! Insufficient balance!");
            return false;
        }
        System.out.println("withdrawing now!");
        balance -= amount;
        return true;
    }

}

class CommandImpl implements Command {
    Commands commands;
    int amount;
    BankAccount bankAccount;

    public CommandImpl(Commands commands, int amount, BankAccount bankAccount) {
        this.commands = commands;
        this.amount = amount;
        this.bankAccount = bankAccount;
    }

    @Override
    public void execute() {
        switch (commands) {
            case DEPOSIT:
                bankAccount.deposit(amount);
                break;
            case WITHDRAW:
                bankAccount.withdraw(amount);
                break;
        }

    }

    @Override
    public void undo() {

    }
}

class Demo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        List<Command> commands = asList(new CommandImpl(DEPOSIT, 500, bankAccount),
                new CommandImpl(WITHDRAW, 200, bankAccount));

        commands.forEach(Command::execute);

    }
}