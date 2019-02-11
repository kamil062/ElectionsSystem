package net;

import main.Elections;
import main.Login;
import main.Vote;

public class Receiver implements Runnable {
	@Override
	public void run() {
		while(true){
			String received = Net.receiveData();
			
			if(received.equals("login")){
				Login log = new Login();
				log.login();
			}
			if(received.equals("electionsList"))
				Elections.getElections();
			if(received.equals("getInfoAboutElections"))
				Elections.getInfoAboutElections();
			
			if(received.equals("getPresidentialElectionsCandidates"))
				Vote.getPresidentialElectionsCandidates();
			if(received.equals("getParliamentaryElectionsCandidates"))
				Vote.getParliamentaryElectionsCandidates();
			if(received.equals("getSenateElectionsCandidates"))
				Vote.getSenateElectionsCandidates();
			
			if(received.equals("canUserVote"))
				Vote.canUserVote();

			if(received.equals("voteInPresidentialElections"))
				Vote.voteInPresidentialElections();
			if(received.equals("voteInParliamentaryElections"))
				Vote.voteInParliamentaryElections();
			if(received.equals("voteInSenateElections"))
				Vote.voteInSenateElections();
		}
	}
}
