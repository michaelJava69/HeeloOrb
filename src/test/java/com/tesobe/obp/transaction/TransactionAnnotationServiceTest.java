package com.tesobe.obp.transaction;

import com.tesobe.obp.AbstractTestSupport;
import com.tesobe.obp.account.Account;
import com.tesobe.obp.account.AccountService;
import com.tesobe.obp.account.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ListIterator;

import static com.tesobe.obp.account.Transaction.Tag;

public class TransactionAnnotationServiceTest extends AbstractTestSupport {
    @Autowired private AccountService accountService;
    @Autowired private MonetaryTransactionsService monetaryTransactionsService;
    @Autowired private TransactionAnnotationService transactionAnnotationService;

    @Test
    public void addTagOk() {
        List<Account> accounts = accountService.fetchPrivateAccounts(authToken, true);
        Account ownAccount = accounts.get(0);
         System.out.println("Transaction test ownAccount account Id "+ownAccount.getId());
     	 System.out.println("Transaction test ownAccount account Label "+ownAccount.getLabel());
   	     System.out.println("Transaction test ownAccount account BankId "+ownAccount.getBankId());
   	     System.out.println("Transaction test  ownAccount  Balance "+ownAccount.getBalance());
   	     
        List<Transaction> transactions = monetaryTransactionsService.fetchTransactionList(authToken, ownAccount);
        System.out.println("transactions  "+transactions);
        
        ListIterator key =transactions.listIterator();
        Transaction trans;
        while(key.hasNext()){
        	 trans = (Transaction)key.next(); 
        	 System.out.println("TRAN Id "+trans.getId());
        	 System.out.println("TRAN  OWN ACC "+trans.getOwnAccount());
        	 System.out.println("TRAN DETAILS "+trans.getDetails());
        	 System.out.println("TRAN METADATA "+trans.getMetadata());
        } 
        
        Transaction tx = transactions.get(0);
        System.out.println("TRAN.GET(0)  "+tx);
        
        
        String tagValue = "food";
        Tag tag = transactionAnnotationService.addTag(authToken, tx, tagValue);
        Assert.assertNotNull(tag.getId());
        Assert.assertEquals(tagValue, tag.getValue());
        List<Tag> newTags = monetaryTransactionsService.getTransactionById(authToken, ownAccount, tx.getId()).getMetadata().getTags();
        Assert.assertTrue(newTags.contains(tag));
    }

    @Test
    public void deleteTagOk() {
        List<Account> accounts = accountService.fetchPrivateAccounts(authToken, true);
        Account ownAccount = accounts.get(0);
        List<Transaction> transactions = monetaryTransactionsService.fetchTransactionList(authToken, ownAccount);

        Transaction tx = transactions.get(0);
        //tx.getMetadata().getTags().forEach(tag -> transactionAnnotationService.deleteTag(authToken, tx, tag));
        Tag tag = transactionAnnotationService.addTag(authToken, tx, "food");
        List<Tag> txTags = monetaryTransactionsService.getTransactionById(authToken, ownAccount, tx.getId()).getMetadata().getTags();
        Assert.assertTrue(txTags.contains(tag));

        transactionAnnotationService.deleteTag(authToken, tx, tag);
        txTags = monetaryTransactionsService.getTransactionById(authToken, ownAccount, tx.getId()).getMetadata().getTags();
        Assert.assertTrue(!txTags.contains(tag));
    }

}