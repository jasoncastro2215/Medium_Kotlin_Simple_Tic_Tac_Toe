import java.lang.NumberFormatException
import kotlin.Exception

fun main() {
    println("""How to play:
X is first player
Entering coordinate - <row><space><column>
Ex. 1 2

    """.trimMargin())

    val list = mutableListOf<MutableList<Char>>()
    var player1Turn = true
    initList(list)
    showBoard(list)
    while (true) {
        try {
            val coordinate = readln().split(" ").map { it.toInt() - 1 }
            if (list[coordinate[0]][coordinate[1]] in listOf('X', 'O'))
                throw Exception()
            list[coordinate[0]][coordinate[1]] = if (player1Turn) 'X' else 'O'
            showBoard(list)
            if (haveWinner(list) || isDraw(list))
                break
            player1Turn = !player1Turn
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        } catch (e: Exception) {
            println("This cell is occupied! Choose another one!")
        }
    }
}

fun haveWinner(list: MutableList<MutableList<Char>>): Boolean {
    //horizontal
    for (row in list) {
        if (row[0] == row[1] && row[0] == row[2] && row[0] in listOf('X', 'O')) {
            print("${if (row[0] == 'X') "X" else "O"} wins")
            return true
        }
    }
    //vertical
    for (i in 0 until 3) {
        if (list[0][i] == list[1][i] && list[0][i] == list[2][i] && list[0][i] in listOf('X', 'O')) {
            print("${if (list[0][i] == 'X') "X" else "O"} wins")
            return true
        }
    }
    //diagonal
    if (list[0][0] == list[1][1] && list[0][0] == list[2][2] && list[0][0] in listOf('X', 'O')) {
        print("${if (list[0][0] == 'X') "X" else "O"} wins")
        return true
    }

    if (list[0][2] == list[1][1] && list[0][2] == list[2][0] && list[0][2] in listOf('X', 'O')) {
        print("${if (list[0][2] == 'X') "X" else "O"} wins")
        return true
    }

    return false
}

fun isDraw(list: MutableList<MutableList<Char>>): Boolean {
    for (row in list) {
        for (coordinate in row) {
            if (coordinate in listOf(' ', '_'))
                return false
        }
    }
    print("Draw")
    return true
}

fun showBoard(list: MutableList<MutableList<Char>>) {
    println("    COLUMN")
    println("    1  2  3  ")
    println("   ---------")
    for (i in list.indices) {
        when (i) {
            0 -> print("R ")
            1 -> print("0 ")
            2 -> print("W ")
        }
        print("${i + 1}| ${list[i].joinToString(" ")} |\n")
    }
    println("   ---------")
}

fun initList(list: MutableList<MutableList<Char>>) {
    for (i in 0 until 3)
        list.add(MutableList(3) { ' ' })
}


