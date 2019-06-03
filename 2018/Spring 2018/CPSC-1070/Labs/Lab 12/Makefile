# Makefile for Lab 12
CC = g++
CFLAGS = -g -Wall

OBJS= polygon.o\
      rectangle.o\
      triangle.o \
      hexagon.o \
      main.o

INCS=polygon.h rectangle.h triangle.h hexagon.h

lab12: ${OBJS}
	@echo
	@echo Linking $@
	g++ -o $@ -Wall -g ${OBJS} -lm

${OBJS}: ${INCS} Makefile

clean:
	rm -f *.o lab12
