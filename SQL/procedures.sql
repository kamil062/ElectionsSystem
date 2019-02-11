CREATE OR REPLACE PROCEDURE loginUser(pLogin IN VARCHAR2, pPass IN VARCHAR2, ret OUT NUMBER, ret2 OUT NUMBER) IS
  user NUMBER;
  utype NUMBER;
  uid NUMBER;
BEGIN
  SELECT count(*) INTO user FROM users WHERE PESEL = pLogin AND password = pPass;
  IF user <> 0 THEN
    SELECT type, id INTO utype, uid FROM users WHERE PESEL = pLogin AND password = pPass;
    ret := utype;
    ret2 := uid;
  ELSE
    ret := -1;
    ret2 := -1;
  END IF;
END;

/   

CREATE OR REPLACE PROCEDURE addUser(pPassword IN VARCHAR2, pName IN VARCHAR2, pSurname IN VARCHAR2, pDateOfBirth IN DATE, pPESEL IN VARCHAR2, pPhoneNumber IN NUMBER, pType IN NUMBER) IS
BEGIN
  INSERT INTO users (id, password, name, surname, dateOfBirth, PESEL, phoneNumber, address, type) 
      VALUES (usersIdSeq.nextval, pPassword, pName, pSurname, pDateOfBirth, pPESEL, pPhoneNumber, addressIdSeq.currval, pType);
END;

/

CREATE OR REPLACE PROCEDURE addAddress(pStreet IN VARCHAR2, pHouseNumber IN VARCHAR2, pFlatnumber IN VARCHAR2, pPostalCode IN VARCHAR2, pCity IN VARCHAR2, pCountry IN VARCHAR2) IS
BEGIN
  INSERT INTO address (id, street, houseNumber, flatNumber, postalCode, city, country) 
      VALUES (addressIdSeq.nextval, pStreet, pHouseNumber, pFlatnumber, pPostalCode, pCity, pCountry);
END;

/

CREATE OR REPLACE PROCEDURE addElections(pType IN NUMBER, pDateStarted IN DATE, pDateEnded IN DATE) IS
BEGIN
  INSERT INTO elections (id, type, dateStarted, dateEnded)
      VALUES(electionsIdSeq.nextval, pType, pDateStarted, pDateEnded);
END;

/

CREATE OR REPLACE PROCEDURE addParty(pName IN VARCHAR2, pAddress IN NUMBER) IS
BEGIN
  INSERT INTO parties (id, name, address) VALUES (partiesIdSeq.nextval, pName, pAddress);
END;

/

CREATE OR REPLACE PROCEDURE addCandidate(pUser IN NUMBER, pParty IN NUMBER) IS
BEGIN
  INSERT INTO candidates (id, usr, party) VALUES (candidatesIdSeq.nextval, pUser, pParty);
END;

/

CREATE OR REPLACE PROCEDURE addPresidentialCandidate(pCandidate IN NUMBER, pElections IN NUMBER) IS
BEGIN
  INSERT INTO presidentialCandidates (id, candidate, elections)
  VALUES (presidentialCandidatesIdSeq.nextval, pCandidate, pElections);
END;

/

CREATE OR REPLACE PROCEDURE addParliamentaryCandidate(pCandidate IN NUMBER, pElections IN NUMBER) IS
BEGIN
  INSERT INTO parliamentaryCandidates (id, candidate, elections)
  VALUES (parliamentaryCandidatesIdSeq.nextval, pCandidate, pElections);
END;

/

CREATE OR REPLACE PROCEDURE addSenateCandidate(pCandidate IN NUMBER, pElections IN NUMBER) IS
BEGIN
  INSERT INTO senateCandidates (id, candidate, elections)
  VALUES (senateCandidatesIdSeq.nextval, pCandidate, pElections);
END;

/

CREATE OR REPLACE PROCEDURE addReferendumQuestion(pQuestion IN VARCHAR2, pElections IN NUMBER) IS
BEGIN
  INSERT INTO referendumQuestions (id, question, elections)
  VALUES (referendumQuestionsIdSeq.nextval, pQuestion, pElections);
END;

/

CREATE OR REPLACE PROCEDURE addPresidentialVote(pElections IN NUMBER, PUser IN NUMBER, pCandidate IN NUMBER) IS
BEGIN
  INSERT INTO presidentialVotes (id, elections, usr, candidate)
  VALUES (presidentialVotesIdSeq.nextval, pElections, pUser, pCandidate);
END;

/

CREATE OR REPLACE PROCEDURE addParliamentaryVote(pElections IN NUMBER, PUser IN NUMBER,  pCandidate IN NUMBER) IS
BEGIN
  INSERT INTO parliamentaryVotes (id, elections, usr, candidate)
  VALUES (parliamentaryVotesIdSeq.nextval, pElections, pUser, pCandidate);
END;

/

CREATE OR REPLACE PROCEDURE addSenateVote(pElections IN NUMBER, PUser IN NUMBER,  pCandidate IN NUMBER) IS
BEGIN
  INSERT INTO senateVotes (id, elections, usr, candidate)
  VALUES (senateVotesIdSeq.nextval, pElections, pUser, pCandidate);
END;

/

CREATE OR REPLACE PROCEDURE addReferendumVote(pElections IN NUMBER, PUser IN NUMBER,  pQuestion IN NUMBER) IS
BEGIN
  INSERT INTO referendumVotes (id, elections, usr, question)
  VALUES (referendumVotesIdSeq.nextval, pElections, pUser, pQuestion);
END;