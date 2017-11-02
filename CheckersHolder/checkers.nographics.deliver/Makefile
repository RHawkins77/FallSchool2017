CC              := gcc
CFLAGS          := -Wall
CPPFLAGS        := -I./ -I/usr/X11R6/include/Xm -I/usr/X11R6/include
#LDFLAGS         := -L/usr/lib/X11R6 -lXm -lXaw -lXmu -lXt -lX11
#LDFLAGS         := -L/usr/X11R6/lib -L /usr/X11R6/LessTif/Motif1.2/lib -lXm -lXaw -lXmu -lXt -lX11 -lICE -lSM -pthread
LDFLAGS         := -lICE -lSM -pthread

# Uncomment this next line if you'd like to compile the graphical version of the checkers server.
#CFLAGS          += -DGRAPHICS

all: checkers computer diff rat depth5 random
checkers: graphics.o
computer: myprog.o
diff: diff.o
rat: rat.o
depth5: depth5.o
random: random.o
	${CC} ${LDFLAGS} ${CPPFLAGS} ${CFLAGS} -o $@ $^


.PHONY: clean
clean:	
	@-rm checkers computer *.o
