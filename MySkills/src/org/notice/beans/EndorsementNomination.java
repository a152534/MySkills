package org.notice.beans;

import java.sql.Date;

public class EndorsementNomination {

	// SELECT * FROM myskills.v_expanded_endorsement_nomination where
	// nominated_endorsee =

	/*
	 * 
	 * view v_expanded_endorsement_nomination as endorsement_nomination_id , User_id
	 * , nominated_endorsee , fullname
	 *
	 */
	
	
	
	
	
	private int endorsementNominationId;
	private String userID;
	private String userName;
	private String endorserUserID;

	public EndorsementNomination(int endorsementNominationId, String userID, String userName, String endorserUserID	) {
		super();
		this.endorsementNominationId = endorsementNominationId;
		this.userID = userID;
		this.userName = userName;
		this.endorserUserID = endorserUserID;

	}

	public int getEndorsementNominationId() {
		return endorsementNominationId;
	}

	public void setEndorsementNominationId(int endorsementNominationId) {
		this.endorsementNominationId = endorsementNominationId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEndorserUserID() {
		return endorserUserID;
	}

	public void setEndorserUserID(String endorserUserID) {
		this.endorserUserID = endorserUserID;
	}

	
}