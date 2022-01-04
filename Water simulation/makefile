# water flow animation
# Segomotso Petlele
# 05 September 2020

JAVAC=/usr/bin/javac

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $<

classes: FlowPanel.class Flow.class \
         Terrain.class \ Water.class 
         

default: $(CLASSES)

clean:
	rm *.class

run: