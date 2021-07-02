package com.rhishi.memento;

import lombok.Data;

@Data
/**
 * the originator
 */
public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withDraw(int amout) {
        this.balance -= amout;

    }

    public MementoApi generateMemento() {
        Memento memento = new Memento();
        memento.setBalance(this.getBalance());
        return memento;
    }

    public void goBackToMemento(MementoApi mementoApi) {
        Memento memento = (Memento) mementoApi;
        this.balance = memento.getBalance();
    }

    private static class Memento implements MementoApi {
        private int balance;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }

}

interface MementoApi {
}

class Demo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        bankAccount.deposit(100);
        System.out.println(bankAccount);

        MementoApi mementoApi = bankAccount.generateMemento();
        bankAccount.withDraw(100);
        System.out.println(bankAccount);
        bankAccount.goBackToMemento(mementoApi);
        System.out.println(bankAccount);


    }
}


