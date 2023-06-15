package minesweeper

import kotlin.random.Random

fun main() {

    var fieldWidth = 9
    var fieldSize = 81
    var map = mutableListOf<String>()
    repeat(fieldSize) {
        map.add(".")
    }

    println("How many mines do you want on the field?")
    var bombByPlayer = readln().toInt()
    val bombToCompare = bombByPlayer
    val bombCount = Random.nextInt(fieldSize)
    while (bombByPlayer > 0) {
        val randomSeed = Random.nextInt(fieldSize)
        if (map[randomSeed] != "X") {
            map[randomSeed] = "X"
            bombByPlayer--
        }


    }
    var seqList: MutableList<MutableList<String>> = ArrayList()
    var linesBombs = map.chunked(9)
    seqList = linesBombs as MutableList<MutableList<String>>

// Стартовая генерация поля
    for (i in seqList.indices) {
        for (c in seqList[i].indices) {
            if (seqList[i][c] == "X") {
                if (i > 0) {

                    if (seqList[i - 1][c] == ".") seqList[i - 1][c] = "1"
                    else if (seqList[i - 1][c] != "X") seqList[i - 1][c] = (seqList[i - 1][c].toInt() + 1).toString()

                    if (c > 0) {
                        if (seqList[i - 1][c - 1] == ".") seqList[i - 1][c - 1] = "1"
                        else if (seqList[i - 1][c - 1] != "X") seqList[i - 1][c - 1] =
                            (seqList[i - 1][c - 1].toInt() + 1).toString()

                    }
                    if (c < seqList[i].size - 1) {
                        if (seqList[i - 1][c + 1] == ".") seqList[i - 1][c + 1] = "1"
                        else if (seqList[i - 1][c + 1] != "X") seqList[i - 1][c + 1] =
                            (seqList[i - 1][c + 1].toInt() + 1).toString()


                    }


                }
                if (i < seqList.size - 1) {
                    if (seqList[i + 1][c] == ".") seqList[i + 1][c] = "1"
                    else if (seqList[i + 1][c] != "X") seqList[i + 1][c] = (seqList[i + 1][c].toInt() + 1).toString()

                    if (c > 0) {
                        if (seqList[i + 1][c - 1] == ".") seqList[i + 1][c - 1] = "1"
                        else if (seqList[i + 1][c - 1] != "X") seqList[i + 1][c - 1] =
                            (seqList[i + 1][c - 1].toInt() + 1).toString()


                    }
                    if (c < seqList[i].size - 1) {
                        if (seqList[i + 1][c + 1] == ".") seqList[i + 1][c + 1] = "1"
                        else if (seqList[i + 1][c + 1] != "X") seqList[i + 1][c + 1] =
                            (seqList[i + 1][c + 1].toInt() + 1).toString()


                    }

                }
                if (c > 0) {
                    if (seqList[i][c - 1] == ".") seqList[i][c - 1] = "1"
                    else if (seqList[i][c - 1] != "X") seqList[i][c - 1] = (seqList[i][c - 1].toInt() + 1).toString()

                }
                if (c < seqList[i].size - 1) {
                    if (seqList[i][c + 1] == ".") seqList[i][c + 1] = "1"
                    else if (seqList[i][c + 1] != "X") seqList[i][c + 1] = (seqList[i][c + 1].toInt() + 1).toString()


                }
            }
        }
    }
    // печать поля
    fun printField() {
        println(" │123456789│")
        println("—│—————————│")
        for (i in 0 until fieldWidth) {
            print("${i + 1}│")
            for (c in 0 until (fieldSize / fieldWidth)) {

                if (seqList[i][c] == "X") print(".")
                else if ("N" in seqList[i][c] && "*" in seqList[i][c]) print(seqList[i][c].dropLast(2))
                else if ("N" in seqList[i][c]) print(seqList[i][c].dropLast(1))
                else if ("*" in seqList[i][c]) print("*")
                else if (seqList[i][c] == "/") print("/")
                else print(".")
            }
            print("│")
            println()
        }
        println("—│—————————│")
    }
    fun printFieldLose() {
        println(" │123456789│")
    println("—│—————————│")
    for (i in 0 until fieldWidth) {
        print("${i + 1}│")
        for (c in 0 until (fieldSize / fieldWidth)) {

            if (seqList[i][c] == "X") print("X")
            else if ("X" in seqList[i][c]) print("X")
            else if ("N" in seqList[i][c] && "*" in seqList[i][c]) print(seqList[i][c].dropLast(2))
            else if ("N" in seqList[i][c]) print(seqList[i][c].dropLast(1))
            else if ("*" in seqList[i][c]) print("*")
            else if (seqList[i][c] == "/") print("/")
            else print(".")
        }
        print("│")
        println()
    }
    println("—│—————————│")
}

    var markedBombCount = 0
    var emptySpotMarked = 0
    fun countEmptySpace() {
            println("цикл пошел")

            printField()
            for (i in seqList.indices) {
                for (c in seqList[i].indices) {
                    println("Начало проверки ряд ${i} и число ${c}")
                    if (seqList[i][c] == "/") {
                        println("Подтверждено пустой слот ${i} и число ${c}")
                        if (i > 0) {
                            println("проверка ряд младше на 1")
                            if (seqList[i - 1][c] == ".") {
                                seqList[i - 1][c] = "/"

                                println("Заполнен новый пустой слот ${i-1} и число $c ")
                            } else if (seqList[i - 1][c] != "X" || seqList[i - 1][c] != "/") {
                                if ("N" !in seqList[i - 1][c])
                                    seqList[i - 1][c] = seqList[i - 1][c] + "N"
                            }

                            if (c > 0) {
                                if (seqList[i - 1][c - 1] == ".") {
                                    seqList[i - 1][c - 1] = "/"

                                    println("Заполнен новый пустой слот ${i-1} и число ${c-1} ")
                                } else if (seqList[i - 1][c - 1] != "X"|| seqList[i - 1][c-1] != "/") {
                                    if ("N" !in seqList[i - 1][c - 1])
                                        seqList[i - 1][c - 1] = seqList[i - 1][c - 1] + "N"
                                }

                            }
                            if (c < seqList[i].size - 1) {
                                if (seqList[i - 1][c + 1] == ".") {
                                    seqList[i - 1][c + 1] = "/"

                                    println("Заполнен новый пустой слот ${i-1} и число ${c+1}")
                                } else if (seqList[i - 1][c + 1] != "X"|| seqList[i - 1][c+1] != "/") {
                                    if ("N" !in seqList[i - 1][c + 1])
                                        seqList[i - 1][c + 1] = seqList[i - 1][c + 1] + "N"
                                }


                            }


                        }
                        if (i < seqList.size - 1) {
                            println("Проверка ряд старше на 1")
                            if (seqList[i + 1][c] == ".") {
                                seqList[i + 1][c] = "/"

                                println("Заполнен новый пустой слот ${i+1} и число $c ")
                            } else if (seqList[i + 1][c] != "X"|| seqList[i + 1][c] != "/") {
                                if ("N" !in seqList[i + 1][c])
                                    seqList[i + 1][c] =
                                        seqList[i + 1][c] + "N"
                            }

                            if (c > 0) {
                                if (seqList[i + 1][c - 1] == ".") {
                                    seqList[i + 1][c - 1] = "/"
                                    println("Заполнен новый пустой слот ${i+1} и число ${c-1} ")

                                } else if (seqList[i + 1][c - 1] != "X" || seqList[i + 1][c-1] != "/") {
                                    if ("N" !in seqList[i + 1][c - 1])
                                        seqList[i + 1][c - 1] =
                                            seqList[i + 1][c - 1] + "N"
                                }


                            }
                            if (c < seqList[i].size - 1) {
                                if (seqList[i + 1][c + 1] == ".") {
                                    seqList[i + 1][c + 1] = "/"

                                    println("Заполнен новый пустой слот ${i+1} и число ${c+1}")
                                } else if (seqList[i + 1][c + 1] != "X" || seqList[i + 1][c-1] != "/") {
                                    if ("N" !in seqList[i + 1][c + 1])
                                        seqList[i + 1][c + 1] =
                                            seqList[i + 1][c + 1] + "N"
                                }


                            }

                        }
                        if (c > 0) {
                            println("проверка на том же ряду, число больше 0")
                            if (seqList[i][c - 1] == ".") {
                                seqList[i][c - 1] = "/"

                                println("Заполнен новый пустой слот ${i} и число ${c-1}")
                            } else if (seqList[i][c - 1] != "X"|| seqList[i][c-1] != "/") {
                                if ("N" !in seqList[i][c - 1])
                                    seqList[i][c - 1] =
                                        seqList[i][c - 1] + "N"
                            }

                        }
                        if (c < seqList[i].size - 1) {
                            println("проверка на том же ряду, чиcло меньше максимума")
                            if (seqList[i][c + 1] == ".") {
                                seqList[i][c + 1] = "/"

                                println("Заполнен новый пустой слот ${i} и число ${c+1}")
                            } else if (seqList[i][c + 1] != "X" || seqList[i][c+1] != "/") {
                                if ("N" !in seqList[i][c + 1]) seqList[i][c + 1] = seqList[i][c + 1] + "N"
                            }
                        }
                    }
                }
            }

    }
    fun countEmptySpaceFlood( x: Int, y: Int) {

        if (x>seqList.size-1 || y>seqList[0].size-1) {

            return
        }
        if (x<0 || y<0) {

            return
        }
        if ("*" in seqList[x][y]) seqList[x][y] = seqList[x][y].dropLast(1)
        if (seqList[x][y] == "/") return

        else if (seqList[x][y] == ".") seqList[x][y] = "/"
        else if (seqList[x][y] == "*" || seqList[x][y] == ".*") seqList[x][y] = "/"
        else if (seqList[x][y] != "X" && seqList[x][y] != "/") {

             if ("N" !in seqList[x][y] && "*" !in seqList[x][y])
                 seqList[x][y] = seqList[x][y] + "N"
             return
        }
        countEmptySpaceFlood( x-1, y-1)
        countEmptySpaceFlood( x-1, y)
        countEmptySpaceFlood( x-1, y+1)
        countEmptySpaceFlood( x, y-1)
        countEmptySpaceFlood( x, y+1)
        countEmptySpaceFlood( x+1, y-1)
        countEmptySpaceFlood( x+1, y)
        countEmptySpaceFlood( x+1, y+1)
        }





    // ход игрока
    fun playerTurn() {
        if ((markedBombCount - emptySpotMarked) == bombToCompare) {
            println("Congratulations! You found all the mines!")
            return
        }
        println("Set/delete mine marks (x and y coordinates):")
        val (y, x, comand) = readln().split(" ")
        val coordY = y.toInt() - 1
        val coordX = x.toInt() - 1
        if (comand == "mine") {
            if ("N" in seqList[coordX][coordY]){
                printField()
                playerTurn()
            }
            else if ("/" in seqList[coordX][coordY]){
                printField()
                playerTurn()
            }
            else if ("*" in seqList[coordX][coordY]){
                seqList[coordX][coordY] = seqList[coordX][coordY].dropLast(1)
                if (seqList[coordX][coordY]=="X") markedBombCount--
                else emptySpotMarked--

            }
                else {
                    seqList[coordX][coordY] = seqList[coordX][coordY]+"*"
                if (seqList[coordX][coordY]=="X") markedBombCount++
                else emptySpotMarked++
            }
            /*
            when (seqList[coordX][coordY]) {
                "X" -> {
                    seqList[coordX][coordY] = "X*"
                    markedBombCount++
                    printField()
                    playerTurn()
                }

                "." -> {
                    seqList[coordX][coordY] = ".*"
                    emptySpotMarked++
                    printField()
                    playerTurn()
                }

                "X*" -> {
                    seqList[coordX][coordY] = "X"
                    markedBombCount--
                    printField()
                    playerTurn()
                }

                "*" -> {
                    seqList[coordX][coordY] = "."
                    emptySpotMarked--
                    printField()
                    playerTurn()
                }


                else -> {
                    seqList[coordX][coordY] = seqList[coordX][coordY] + "*"
                    printField()
                    playerTurn()
                }
            }

             */

        } else if (comand == "free") {
            when (seqList[coordX][coordY]) {
                "X" -> {
                    seqList[coordX][coordY] = "XN"
                    printFieldLose()
                    println("You stepped on a mine and failed!")
                    return
                }

                "." -> {
                    emptySpotMarked++
                    countEmptySpaceFlood(coordX,coordY)
                    printField()
                    playerTurn()
                }
                "*" -> {
                    emptySpotMarked--
                    countEmptySpaceFlood(coordX,coordY)
                    printField()
                    playerTurn()
                }

                "X*" -> {
                    seqList[coordX][coordY] = "XN"
                    printFieldLose()
                    println("You stepped on a mine and failed!")
                    return
                }
                else -> {
                    seqList[coordX][coordY] = seqList[coordX][coordY]+"N"
                    printField()
                    playerTurn()
                }
            }
            if ("*" in seqList[coordX][coordY]) {
                seqList[coordX][coordY] = seqList[coordX][coordY].dropLast(1)
                printField()
                playerTurn()
            }

        }








        printField()
        playerTurn()
    }
    printField()
    playerTurn()
}