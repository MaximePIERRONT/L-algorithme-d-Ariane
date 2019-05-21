JFLAGS = -g -implicit:none
JC = javac
JVM= java
FILE=

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

	$(JC) $*.java

CLASSES= \
		ActionDemarrePartie.java\
		ActionChoisirGrille.java \
		ActionQuitter.java \
		ActionMenu.java \
		AttenteToucheDeterministeManuel.java \
		AttenteToucheAleatoireManuel.java \
		Cell.java \
		Constructeur.java \
		ActionCreerGrille.java \
		ConfigurationParametresPartie.java \
		FenetreJeu.java \
		Fond.java \
		Grille.java \
		GrilleDeConstructeur.java \
		GrilleGraphique.java \
		CelluleDeConstructeur.java \
		LeftPaneDurantPartie.java \
		LeftPaneMenuPrePartie.java \
		ObservateurConstructeur.java \
		MouvementAleatoire.java \
		MouvementDeterministe.java \
		Moyenne.java \
		Thesee.java \
		Vertex.java

MAIN = FenetreJeu

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)
clean :
	$(RM) *.class
