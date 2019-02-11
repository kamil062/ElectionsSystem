DROP TABLE address CASCADE CONSTRAINTS
/
DROP TABLE users CASCADE CONSTRAINTS
/
DROP TABLE elections CASCADE CONSTRAINTS
/
DROP TABLE parties CASCADE CONSTRAINTS
/
DROP TABLE candidates CASCADE CONSTRAINTS
/
DROP TABLE presidentialCandidates CASCADE CONSTRAINTS
/
DROP TABLE parliamentaryCandidates CASCADE CONSTRAINTS
/
DROP TABLE senateCandidates CASCADE CONSTRAINTS
/
DROP TABLE referendumQuestions CASCADE CONSTRAINTS
/
DROP TABLE presidentialVotes CASCADE CONSTRAINTS
/
DROP TABLE parliamentaryVotes CASCADE CONSTRAINTS
/
DROP TABLE senateVotes CASCADE CONSTRAINTS
/
DROP TABLE referendumVotes CASCADE CONSTRAINTS
/
CREATE TABLE address (
  id          NUMBER(10)    CONSTRAINT addressIdPK          PRIMARY KEY,
  street      VARCHAR2(50)  CONSTRAINT addressStreetNN      NOT NULL,
  houseNumber VARCHAR2(10)  CONSTRAINT addressHouseNumberNN NOT NULL,
  flatNumber  VARCHAR2(10)  CONSTRAINT addressFlatNumberNN  NOT NULL,  
  postalCode  VARCHAR2(6)   CONSTRAINT addressPostalCodeNN  NOT NULL,
  city        VARCHAR2(50)  CONSTRAINT addressCityNN        NOT NULL,
  country     VARCHAR2(50)  CONSTRAINT addressCountryNN     NOT NULL
)
/
CREATE TABLE users (
  id          NUMBER(10)    CONSTRAINT  usersIdPK           PRIMARY KEY,
  password    VARCHAR2(50)  CONSTRAINT  usersPasswordNN     NOT NULL,
  name        VARCHAR2(20)  CONSTRAINT  usersNameNN         NOT NULL,
  surname     VARCHAR2(20)  CONSTRAINT  usersSurnameNN      NOT NULL,
  dateOfBirth DATE          CONSTRAINT  usersDateOfBirthNN  NOT NULL,
  PESEL       VARCHAR2(11)  CONSTRAINT  usersPESELNN        NOT NULL
                            CONSTRAINT  usersPESELU         UNIQUE,
  phoneNumber NUMBER(10)    CONSTRAINT  usersPhoneNumberNN  NOT NULL,
  address     NUMBER(10)    CONSTRAINT  usersAddressFK      REFERENCES address(id),
  type        NUMBER(1)     CONSTRAINT  usersTypeNN         NOT NULL                  -- 1 - wyborca, 2 - czlonek komisji, 3 - urzednik 
)
/
CREATE TABLE elections (
  id          NUMBER(10)  CONSTRAINT electionsIdPK          PRIMARY KEY,
  type        NUMBER(1)   CONSTRAINT electionsTypeNN        NOT NULL,       --1 - prezydenckie, 2 - do sejmu, 3 - do senatu, 4 - referendum
  dateStarted DATE        CONSTRAINT electionsDateStartedNN NOT NULL,
  dateEnded   DATE        CONSTRAINT electionsDateEndedNN   NOT NULL
)
/
CREATE TABLE parties (
  id      NUMBER(10)    CONSTRAINT  partiesIdPK       PRIMARY KEY,
  name    VARCHAR(100)  CONSTRAINT  partiesNameNN     NOT NULL,
  address NUMBER(10)    CONSTRAINT  partiesAddressFK  REFERENCES address(id)
)
/
CREATE TABLE candidates (
  id    NUMBER(10)  CONSTRAINT  candidatesIdPK    PRIMARY KEY,
  usr   NUMBER(10)  CONSTRAINT  candidatesUserFK  REFERENCES users(id),
  party NUMBER(10)  CONSTRAINT  candidatesPartyFK REFERENCES parties(id)
)
/
CREATE TABLE presidentialCandidates (
  id        NUMBER(10)  CONSTRAINT  PCIdPK         PRIMARY KEY,
  candidate NUMBER(10)  CONSTRAINT  PCCandidateFK  REFERENCES candidates(id),
  elections NUMBER(10)  CONSTRAINT  PCElectionsFK  REFERENCES elections(id)
);
/
CREATE TABLE parliamentaryCandidates (
  id        NUMBER(10)  CONSTRAINT  PC2IdPK         PRIMARY KEY,
  candidate NUMBER(10)  CONSTRAINT  PC2CandidateFK  REFERENCES candidates(id),
  elections NUMBER(10)  CONSTRAINT  PC2ElectionsFK  REFERENCES elections(id)
);
/
CREATE TABLE senateCandidates (
  id        NUMBER(10)  CONSTRAINT  SCIdPK         PRIMARY KEY,
  candidate NUMBER(10)  CONSTRAINT  SCCandidateFK  REFERENCES candidates(id),
  elections NUMBER(10)  CONSTRAINT  SCElectionsFK  REFERENCES elections(id)
);
/
CREATE TABLE referendumQuestions (
  id        NUMBER(10)    CONSTRAINT  ROIdPK          PRIMARY KEY,
  question  VARCHAR2(500) CONSTRAINT  ROCandidateNN   NOT NULL,
  elections NUMBER(10)    CONSTRAINT  ROElectionsFK   REFERENCES elections(id)
);
/
CREATE TABLE presidentialVotes (
  id        NUMBER(10)  CONSTRAINT  PVIdPK        PRIMARY KEY,
  elections NUMBER(10)  CONSTRAINT  PVElectionsFK REFERENCES elections(id),
  usr       NUMBER(10)  CONSTRAINT  PVUserFK      REFERENCES users(id),
  candidate NUMBER(10)  CONSTRAINT  PVCandidateFK REFERENCES presidentialCandidates(id)
);
/
CREATE TABLE parliamentaryVotes (
  id        NUMBER(10)  CONSTRAINT  PV2IdPK         PRIMARY KEY,
  elections NUMBER(10)  CONSTRAINT  PV2ElectionsFK  REFERENCES elections(id),
  usr       NUMBER(10)  CONSTRAINT  PV2UserFK       REFERENCES users(id),
  candidate NUMBER(10)  CONSTRAINT  PV2CandidateFK  REFERENCES parliamentaryCandidates(id)
);
/
CREATE TABLE senateVotes (
  id        NUMBER(10)  CONSTRAINT  SVIdPK        PRIMARY KEY,
  elections NUMBER(10)  CONSTRAINT  SVElectionsFK REFERENCES elections(id),
  usr       NUMBER(10)  CONSTRAINT  SVUserFK      REFERENCES users(id),
  candidate NUMBER(10)  CONSTRAINT  SVCandidateFK REFERENCES senateCandidates(id)
);
/
CREATE TABLE referendumVotes (
  id        NUMBER(10)  CONSTRAINT  RVIdPK        PRIMARY KEY,
  elections NUMBER(10)  CONSTRAINT  RVElectionsFK REFERENCES elections(id),
  usr       NUMBER(10)  CONSTRAINT  RVUserFK      REFERENCES users(id),
  question  NUMBER(10)  CONSTRAINT  RVQuestionFK  REFERENCES referendumQuestions(id)
);
