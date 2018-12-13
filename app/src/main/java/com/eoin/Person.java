package com.eoin;


class Person{
	private String name;
	private String phone;
    private String gender;
    private String staffNumber;
	
	public Person(String n, String p, String g, String sn){
        name = n;
		phone = p;
        gender = g;
        staffNumber = cleanStaffNumber(sn);
    }
    
    public String cleanStaffNumber(String sn){

        if (sn.length() != 5){
            staffNumber = "ERROR";
        }
        else{
            staffNumber = sn;
        }
        return staffNumber;
    }

	public String getName(){return name.toUpperCase();}
	public String getPhone(){return phone;}
    public String getGender(){return gender;}
    public String getStaffNumber(){return staffNumber;}
	
	public String toString(){
		return (name +" " + phone +" "+ gender +" "+ staffNumber);
	}
}