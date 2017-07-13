package com.tesobe.obp.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tesobe.obp.MoneyJson;
import lombok.Data;
import org.joda.money.Money;

@Data
public class Account {
    private String id;
    private String label;
    @JsonProperty("bank_id")
    private String bankId;

    @JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
    private Money balance;

    private String type;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("swist_bic")
    private String bic;

	public Money getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public void setBalance(Money balance) {
		this.balance = balance;
	}

}
