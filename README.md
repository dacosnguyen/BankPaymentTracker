# Bank Payment Tracker
Bank Payment Tracker (BPT) is an CLI application which tracks bank payments.

## How to start the Bank Payment Tracker
Start the application from `com.mycompany.App`

## Features
- BPT can optionally load data from a text file.
- BPT can afterwards load data from a standard input.
- BPT outputs account balances to standard output every minute. Accounts with 0 balance are hidden.
- BPT is thread-safe. BPT can handle multiple threads in parallel which updates account balances.

## Supported currencies
- USD, EUR, GBP, CZK, VND

## Example
Blue bold texts are inputs entered by an user.
<pre>
[Optional] Enter a file path to a text files with transfers (to skip just press ENTER): 
<b style="color:blue">/home/xxx/BankPaymentTracker/core/src/main/resources/transfers_example.txt</b>
File loaded successfully.

Enter a bank transfer and confirm it by pressing ENTER or quit by typing QUIT:

Account balances info at 2020-01-14T14:46:37.186349
EUR 2000
USD 900
CZK 300

Account balances info at 2020-01-14T14:46:42.184944
EUR 2000
USD 900
CZK 300
<b style="color:blue">USD -100</b>

Account balances info at 2020-01-14T14:46:47.184933
EUR 2000
USD 800
CZK 300
<b style="color:blue">CZK -300</b>

Account balances info at 2020-01-14T14:46:52.185009
EUR 2000
USD 800

Account balances info at 2020-01-14T14:46:57.185104
EUR 2000
USD 800
<b style="color:blue">VND 150</b>

Account balances info at 2020-01-14T14:47:02.184958
EUR 2000
VND 150
USD 800
<b style="color:blue">asd a</b>
Invalid bank transfer format!

Account balances info at 2020-01-14T14:47:07.185031
EUR 2000
VND 150
USD 800
</pre>