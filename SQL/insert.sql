EXECUTE addAddress('Kasztanowa', '5', '0', '25-555', 'Kielce', 'Polska');
EXECUTE addUser('pass', 'Jan', 'Kowalski', TO_DATE('20.10.1994', 'dd.mm.yyyy'), '94102011110', 564851245, 1);

EXECUTE addAddress('Wrzosowa', '20', '5', '25-435', 'Kielce', 'Polska');
EXECUTE addUser('pass', 'Adam', 'Nowak', TO_DATE('10.04.1985', 'dd.mm.yyyy'), '95102011110', 685424625, 2);

EXECUTE addAddress('Kielecka', '10', '0', '00-253', 'Warszawa', 'Polska');
EXECUTE addUser('pass', 'Andrzej', 'Królikowski', TO_DATE('24.12.1976', 'dd.mm.yyyy'), '96102011110', 795346524, 3);

EXECUTE addUser('pass', 'Marcin', 'Wiœniewski', TO_DATE('20.10.1991', 'dd.mm.yyyy'), '91102011110', 564851245, 1);
EXECUTE addUser('pass', 'Maciej', 'D¹browski', TO_DATE('20.10.1990', 'dd.mm.yyyy'), '90102011110', 564851245, 1);
EXECUTE addUser('pass', 'Józef', 'Lewandowski', TO_DATE('20.10.1989', 'dd.mm.yyyy'), '89102011110', 564851245, 1);
EXECUTE addUser('pass', 'Kamil', 'Wójcik', TO_DATE('20.10.1988', 'dd.mm.yyyy'), '88102011110', 564851245, 1);
EXECUTE addUser('pass', 'Wojciech', 'Kamiñski', TO_DATE('20.10.1987', 'dd.mm.yyyy'), '87102011110', 564851245, 1);
EXECUTE addUser('pass', 'Karol', 'Kowalczyk', TO_DATE('20.10.1986', 'dd.mm.yyyy'), '86102011110', 564851245, 1);
EXECUTE addUser('pass', 'Bartosz', 'Zieliñski', TO_DATE('20.10.1985', 'dd.mm.yyyy'), '85102011110', 564851245, 1);
EXECUTE addUser('pass', 'Marcin', 'Szymañski', TO_DATE('20.10.1984', 'dd.mm.yyyy'), '84102011110', 564851245, 1);
EXECUTE addUser('pass', 'Maciej', 'WoŸniak', TO_DATE('20.10.198', 'dd.mm.yyyy'), '83102011110', 564851245, 1);
EXECUTE addUser('pass', 'Józef', 'Koz³owski', TO_DATE('20.10.1994', 'dd.mm.yyyy'), '82102011110', 564851245, 1);
EXECUTE addUser('pass', 'Kamil', 'Jankowski', TO_DATE('20.10.1994', 'dd.mm.yyyy'), '81102011110', 564851245, 1);
EXECUTE addUser('pass', 'Wojciech', 'Wojciechowski', TO_DATE('20.10.1994', 'dd.mm.yyyy'), '80102011110', 564851245, 1);

--------------------------------------------------------------------------------

EXECUTE addElections(1, TO_DATE('29.01.2016 07:00', 'dd.mm.yyyy hh24:mi'), TO_DATE('29.01.2016 21:00', 'dd.mm.yyyy hh24:mi'));
EXECUTE addElections(2, TO_DATE('01.02.2016 00:01', 'dd.mm.yyyy hh24:mi'), TO_DATE('01.02.2016 23:59', 'dd.mm.yyyy hh24:mi'));
EXECUTE addElections(3, TO_DATE('30.01.2016 07:00', 'dd.mm.yyyy hh24:mi'), TO_DATE('30.01.2016 21:00', 'dd.mm.yyyy hh24:mi'));
EXECUTE addElections(4, TO_DATE('02.02.2016 07:00', 'dd.mm.yyyy hh24:mi'), TO_DATE('02.02.2016 21:00', 'dd.mm.yyyy hh24:mi'));

--------------------------------------------------------------------------------

EXECUTE addParty('Polska Partia In¿ynierii Programowania', 1);
EXECUTE addParty('Stowarzyszenie Informatyków', 2);
EXECUTE addParty('Partia 3', 3);
EXECUTE addParty('Partia 4', 1);
EXECUTE addParty('Partia 5', 2);

--------------------------------------------------------------------------------

EXECUTE addCandidate(4, 1);
EXECUTE addCandidate(5, 2);
EXECUTE addCandidate(6, 3);
EXECUTE addCandidate(7, 4);
EXECUTE addCandidate(8, 5);
EXECUTE addCandidate(9, 1);
EXECUTE addCandidate(10, 2);
EXECUTE addCandidate(11, 3);
EXECUTE addCandidate(12, 4);
EXECUTE addCandidate(13, 5);
EXECUTE addCandidate(14, 1);
EXECUTE addCandidate(15, 2);

--------------------------------------------------------------------------------

EXECUTE addPresidentialCandidate(1, 1);
EXECUTE addPresidentialCandidate(2, 1);
EXECUTE addPresidentialCandidate(3, 1);
EXECUTE addPresidentialCandidate(4, 1);

EXECUTE addParliamentaryCandidate(5, 2);
EXECUTE addParliamentaryCandidate(6, 2);
EXECUTE addParliamentaryCandidate(7, 2);
EXECUTE addParliamentaryCandidate(8, 2);
EXECUTE addParliamentaryCandidate(5, 2);
EXECUTE addParliamentaryCandidate(6, 2);
EXECUTE addParliamentaryCandidate(7, 2);
EXECUTE addParliamentaryCandidate(8, 2);

EXECUTE addSenateCandidate(9, 3);
EXECUTE addSenateCandidate(10, 3);
EXECUTE addSenateCandidate(11, 3);
EXECUTE addSenateCandidate(12, 3);

EXECUTE addReferendumQuestion('Czy wstawic wszystkim studentom 3?', 4);
EXECUTE addReferendumQuestion('Czy wstawic wszystkim studentom 4?', 4);
EXECUTE addReferendumQuestion('Czy wstawic wszystkim studentom 5?', 4);

--------------------------------------------------------------------------------

EXECUTE addPresidentialVote(1, 1, 1);
EXECUTE addPresidentialVote(1, 2, 1);
EXECUTE addPresidentialVote(1, 3, 1);
EXECUTE addPresidentialVote(1, 4, 2);
EXECUTE addPresidentialVote(1, 5, 3);

EXECUTE addParliamentaryVote(2, 1, 2);
EXECUTE addParliamentaryVote(2, 2, 1);
EXECUTE addParliamentaryVote(2, 3, 1);
EXECUTE addParliamentaryVote(2, 4, 3);
EXECUTE addParliamentaryVote(2, 5, 4);

EXECUTE addSenateVote(3, 1, 4);
EXECUTE addSenateVote(3, 2, 4);
EXECUTE addSenateVote(3, 3, 1);
EXECUTE addSenateVote(3, 4, 2);
EXECUTE addSenateVote(3, 5, 3);

EXECUTE addReferendumVote(4, 1, 1);
EXECUTE addReferendumVote(4, 2, 2);
EXECUTE addReferendumVote(4, 3, 3);
EXECUTE addReferendumVote(4, 4, 1);
EXECUTE addReferendumVote(4, 5, 1);