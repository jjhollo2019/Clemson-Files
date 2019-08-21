class Money{
    Money(); 

    ~Money();

    Money(double amount); 

    Money(int dollars, int cents);

    void set (int dollars, int cents);

    int getDollars( );

    int getCents( );

    int valueInCents( );

    double dollarsAndCents( );

    Money add(Money otherAmount);

    int dollars;
    int cents;
};