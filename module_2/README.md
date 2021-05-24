# MODULE 2
***
## * If you want to run the program, open folder module_2 and just click on run.bat **BUT not recommended because ANSI encoding is not recognized**
## * _it is assumed that the user already has maven installed_

## * A SMALL NOTE ABOUT OF THE PROGRAM (ABOUT EACH TASK):
#  * TASK 1 
##   The dates are read from the file _(inputDates.txt)_ and then the date format is checked, the correct formats are:
##      yyyy/mm/dd;
##      dd/mm/yyyy;
##      mm-dd-yyyy.
##  Allowable input period of the year from 1900 to 2099, dates with the correct format are written to another file _(outputDates.txt)_, we also remove delimiters from each date.
***
#   * TASK 2
##  The names are also read from the file _(inputNames.txt)_, then we look for the first unique name and write it to another file _(outputNames.txt)_.
##  COMPLEXITY: In this algorithm for finding the first unique name, we iterate over all the elements of the list using a loop from 0 to (N-1), N => size of the list -> O(N). Also, since there are no inner loops -> the total number of loop iterations is N.
##  Adding to Set -> O (1), adding to the list -> O (N), removing from the list -> O (N).
##  This determines the overall complexity of the O (N) algorithm depending on the number of names in the list.
***
#  * TASK 3
##  In this case, we read from the file _(inputCities.txt)_ the number of cities, their name, the number of neighbors and connections with them, the number of paths to be found and, accordingly (city - the beginning of the path and the city - the end of the path). The result, the shortest path, is written to a file _(outputCities.txt)_.
##  For the example presented in the program, this graph corresponds:
![alt-текст](https://github.com/SofiaHalamai/nix-4/blob/main/module_2/src/main/resources/images/graph.png "graph")

##  The matrix of links is depicted using the table:
| Gdansk | Bydgoszcz | Toruń | Warsaw | Kharkov |
|:------:|:---------:|:-----:|:------:|:-------:|
|    0   |     1     |   3   |    0   |    0    |
|    1   |     0     |   1   |    4   |    0    |
|    3   |     1     |   0   |    1   |    5    |
|    0   |     4     |   1   |    0   |    4    |
|    0   |     0     |   5   |    4   |    0    |

##  The shortest path from Gdansk to Warsaw:
![alt-текст](https://github.com/SofiaHalamai/nix-4/blob/main/module_2/src/main/resources/images/way_from_Gdansk_to_Warsaw.png "path from Gdansk to Warsaw")

##  The shortest path from Bydgoszcz to Warsaw:
![alt-текст](https://github.com/SofiaHalamai/nix-4/blob/main/module_2/src/main/resources/images/way_from_Bydgoszcz_to_Warsaw.png "path from Bydgoszcz to Warsaw")

##  The shortest path from Gdansk to Kharkov:
![alt-текст](https://github.com/SofiaHalamai/nix-4/blob/main/module_2/src/main/resources/images/way_from_Gdansk_to_Kharkov.png "path from Gdansk to Kharkov")

##  The shortest path from Warsaw to Kharkov:
![alt-текст](https://github.com/SofiaHalamai/nix-4/blob/main/module_2/src/main/resources/images/way_from_Warsaw_to_Kharkov.png "path from Warsaw to Kharkov")

***
