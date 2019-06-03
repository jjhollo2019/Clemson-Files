#  Author: Jeremy Holloway jjhollo
#  CPSC-1071-001
#  Lab 8
#  Due Date: 3/10/18
#  Description: This is a makefile for compiling lab 8


# makefile for img
OBJS = main.o \
       pixel.o \
       redgreen.o

img: ${OBJS}
	@echo 
	@echo Linking $@
	gcc -o $@ -Wall -g ${OBJS}

.c.o: $<
	@echo
	@echo Compiling $*.c
	gcc -c -Wall -c -g $< 2> ${@:.o=.err}
	@cat $*.err

${OBJS}: image.h makefile

clean: 
	rm *.err *.o img


