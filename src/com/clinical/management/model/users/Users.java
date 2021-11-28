package com.clinical.management.model.users;

import java.util.ArrayList;
import java.util.List;

public class Users {

	private List<User> listUsers = new ArrayList<>();
	
	public Users() {
		
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public boolean addUser(User i) {
		for(User j: this.listUsers) {
			if(j.getName() == i.getName() || j.getCpf() == i.getCpf()) {
				return false;
			}
		}
		
		this.listUsers.add(i);
		return true;
	}
	
	public boolean removeUser(User i) {
		for(int j = 0; j < this.listUsers.size(); j++) {
			if(this.listUsers.get(j).getName().equals(i.getName()) || this.listUsers.get(j).getCpf().equals(i.getCpf())) {
				this.listUsers.remove(j);
				return true;
			}
		}
		return false;
	}
	
	public void showUser() {
		for(User u: this.listUsers) {
			System.out.println(u.getName());
		}
	}
}
