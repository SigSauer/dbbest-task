Water Pipeline System (CLI version)

Water Pipeline System implements an algorithm for finding the optimal path (route)
between points to improve the efficiency of the water supply system. The method of
calculating the shortest path is based on Dijkstra's algorithm by representing water
pipes as edges of a graph, and points as vertices of a graph. When calculating a 
route between non-adjacent points, all possible routes through other points are 
calculated and compared. If the computed path is shorter than the current path, it 
becomes optimal. So, if a path exists, the algorithm will find the shortest path 
between arbitrary points.


User Guide
For the software to work, you need to download two .csv files: the first file contains
information about pipes (start point, end point and pipe length), the second file contains
tasks for the calculation (start point and end point of the route). To download files, you
must specify the path of the file location in the device file system. After executing the
program, the calculation results are saved in a file.



-------------------------------------------------------------------------------------------
Система Водопровода (Консольный интерфейс)

Программное обеспечение систмеы водопровода реализует алгоритм поиска оптимального пути
между пунктами для повышения эфективности работы системы водопровода. Метод вычесления найменьшего
пути осуществляется на основе алгоритма Дейкстры за счет представления водопроводных труб как ребер
граф, а точек - как вершин графа. При вычислении маршрута между не соседними точками, вычисляются  
и сравниваются все возможные маршруты через другие точки. Если вычисленный путь короче, чем текущий, 
он становится оптимальным. Итого, если путь существует, алгоритм найдет самый короткий путь между 
произвольными точками.

Руководство пользователя
Для работы программы, необходимо загрузить два файла формата ".csv": первый файл содержит информацию
о трубах (начальная точка, конечная точка и длина трубы), второй файл содержит задачи для вычисления
(начальная точка и конечная точка маршрута). Для загрузки файлов необходимо указать путь расположения
файла в файловой системе устройства. После выполнения программы, результаты вычислений сохраняются в файле.
