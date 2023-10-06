echo "\n---------------Welcome to the CA341 Phonebook App!---------------"
echo '\nPress any [key] to continue\n'
while [ true ] ; do
read key
if [ $? = 0 ] ; then
echo '\nWould you like to use the Procedural or Object-Oriented version of the program?\n'
echo '"1" --> Procedural \n"2" --> Object-Oriented \n'
echo 'Enter your pick: '
read num
if [ $num = "1" ];
then
   cd ..
   cd "$PWD/src"
   clear
   echo '[Please wait for the "procedural" phonebook to load...]\n' 
   gcc cbook.c
   ./a.out
   rm a.out
elif [ $num = "2" ]
then
 cd ..
 cd "$PWD/src"
 clear
 echo '[Please wait for the "objected-oriented" phonebook to load...]\n' 
 javac oo.java
 java oo
else
 clear
 echo 'Wrong key please try again.\n' 
 sh phonebook.sh
 exit
fi
exit
else
echo " "
fi
done
