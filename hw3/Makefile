JC = javac
SRCPATH = src
BINPATH = bin
PACKAGE = game
JFLAGS = -d $(BINPATH) -sourcepath $(SRCPATH)
TARGET = PlayGame

all: $(BINPATH)/$(PACKAGE)/$(TARGET).class

$(BINPATH)/$(PACKAGE)/%.class: $(SRCPATH)/$(PACKAGE)/%.java
	@echo "Compiling Source Codes From src/ Into bin/"
	$(JC) $(JFLAGS) $<

run: $(BINPATH)/$(PACKAGE)/$(TARGET).class
	@java -cp $(BINPATH) $(PACKAGE).$(TARGET)

clean:
	@echo "Deleting Files From bin/"
	rm $(BINPATH)/* -rf

