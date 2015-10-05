JC = javac
SRCPATH = src
JFLAGS = -d . -sourcepath $(SRCPATH)
TARGET = PlayGame

all: $(TARGET).class

%.class: $(SRCPATH)/%.java
	$(JC) $(JFLAGS) $<

run: $(TARGET).class
	java $(TARGET)

clean:
	rm *.class -rf

