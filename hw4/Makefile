JC = javac
SRCPATH = src
BINPATH = bin
CLASSPATH = lib
PACKAGE = game
JFLAGS = -d $(BINPATH) -sourcepath $(SRCPATH) -cp $(CLASSPATH)
TARGET = POOCasino

all: $(BINPATH)/$(PACKAGE)/$(TARGET).class $(BINPATH)/PlayerB03902015.class

$(BINPATH)/$(PACKAGE)/%.class: $(SRCPATH)/$(PACKAGE)/%.java
	@echo "Compiling Source Codes From src/ Into bin/"
	$(JC) $(JFLAGS) $<

$(BINPATH)/%.class: $(SRCPATH)/%.java
	@echo "Compiling PlayerB03902015 From src/ Into bin/"
	$(JC) $(JFLAGS) $<

run: $(BINPATH)/$(PACKAGE)/$(TARGET).class
	@java -cp $(BINPATH):$(CLASSPATH) $(PACKAGE).$(TARGET) 100 1000 PlayerB03902015 PlayerB03902007 PlayerB03902015 PlayerB03902007

clean:
	@echo "Deleting Files From bin/"
	rm $(BINPATH)/* -rf

