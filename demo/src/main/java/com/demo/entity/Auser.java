package com.demo.entity;

public class Auser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AUSER.ANAME
     *
     * @mbg.generated Tue May 25 18:54:54 CST 2021
     */
    private String aname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AUSER.APWD
     *
     * @mbg.generated Tue May 25 18:54:54 CST 2021
     */
    private String apwd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AUSER.ANAME
     *
     * @return the value of AUSER.ANAME
     *
     * @mbg.generated Tue May 25 18:54:54 CST 2021
     */
    public String getAname() {
        return aname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AUSER.ANAME
     *
     * @param aname the value for AUSER.ANAME
     *
     * @mbg.generated Tue May 25 18:54:54 CST 2021
     */
    public void setAname(String aname) {
        this.aname = aname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AUSER.APWD
     *
     * @return the value of AUSER.APWD
     *
     * @mbg.generated Tue May 25 18:54:54 CST 2021
     */
    public String getApwd() {
        return apwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AUSER.APWD
     *
     * @param apwd the value for AUSER.APWD
     *
     * @mbg.generated Tue May 25 18:54:54 CST 2021
     */
    public void setApwd(String apwd) {
        this.apwd = apwd;
    }
    public String toString() {
    	return "[ auser :"+aname+" apwd"+"xxx ]";
    }
}