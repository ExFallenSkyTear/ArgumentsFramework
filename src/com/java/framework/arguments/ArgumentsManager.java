package com.java.framework.arguments;

import java.util.HashMap;

public class ArgumentsManager {
	private HashMap<String, String> parametersList = new HashMap<String, String>();
	private HashMap<String, Boolean> flagList = new HashMap<String, Boolean>();
	
	private String parameterSymbol;
	private String flagSymbol;
	private String valueSymbol;
	
	private Boolean isAutoGenerationEnabled;
	
	public ArgumentsManager(String flagSymbol, String parameterSymbol, String valueSymbol, Boolean isAutoGenerationEnabled) {
		this.setFlagSymbol(flagSymbol);
		this.setParameterSymbol(parameterSymbol);
		this.setValueSymbol(valueSymbol);
		this.autoGeneration(isAutoGenerationEnabled);
	}
	
	public void parseArguments(String[] args) {
		for (String arg : args) {
			if (this.isArgAParameter(arg)) {
				this.setParValue(this.getParName(arg), getParValue(arg), this.autoGeneration());
			} else if (this.isArgAFlag(arg)) {
				this.setFlagValue(this.getFlagName(arg), true, this.autoGeneration());
			}
		}
	}
	
	private Boolean isArgAParameter(String arg) {
		return arg.startsWith(this.getParameterSymbol()) &&
				       (arg.contains(this.getValueSymbol()) && !arg.endsWith(this.getValueSymbol())) &&
				       ((arg.indexOf(this.getValueSymbol()) - arg.indexOf(this.getParameterSymbol()) + this.getParameterSymbol().length()) > 0);
	}
	
	private Boolean isArgAFlag(String arg) {
		return arg.startsWith(this.getFlagSymbol());
	}
	
	private String getFlagName(String arg) {
		return arg.substring(arg.indexOf(this.getFlagSymbol()) + this.getFlagSymbol().length());
	}
	
	private String getParName(String arg) {
		return arg.substring(arg.indexOf(this.getParameterSymbol()) + this.getParameterSymbol().length(), arg.indexOf(this.getValueSymbol()));
	}
	
	private String getParValue(String arg) {
		return arg.substring(arg.indexOf(this.getValueSymbol()) + this.getValueSymbol().length());
	}
	
	private void setParValue(String name, String value, Boolean isCreationAllowed) {
		if (parametersList.containsKey(name) || isCreationAllowed) parametersList.put(name, value);
	}
	
	private void setFlagValue(String name, Boolean value, Boolean isCreationAllowed) {
		if (flagList.containsKey(name) || isCreationAllowed) flagList.put(name, value);
	}
	
	public String getParameter(String name) {
		return parametersList.containsKey(name) ? parametersList.get(name): null;
	}
	
	public Boolean getFlag(String name) {
		return flagList.containsKey(name) || flagList.get(name);
	}
	
	public void newParameter(String name, String value) {
		parametersList.put(name, value);
	}
	
	public void newParameter(String name) {
		parametersList.put(name, null);
	}
	
	public void newFlag(String name, Boolean value) {
		flagList.put(name, value);
	}
	
	public void newFlag(String name) {
		flagList.put(name, false);
	}
	
	private void setFlagSymbol(String symbol) {
		this.flagSymbol = symbol;
	}
	
	public String getFlagSymbol() {
		return this.flagSymbol;
	}
	
	private void setParameterSymbol(String symbol) {
		this.parameterSymbol = symbol;
	}
	
	public String getParameterSymbol() {
		return this.parameterSymbol;
	}
	
	private void setValueSymbol(String symbol) {
		this.valueSymbol = symbol;
	}
	
	public String getValueSymbol() {
		return this.valueSymbol;
	}
	
	private void autoGeneration(Boolean autogen) {
		this.isAutoGenerationEnabled = autogen;
	}
	
	public Boolean autoGeneration() {
		return this.isAutoGenerationEnabled;
	}
}
