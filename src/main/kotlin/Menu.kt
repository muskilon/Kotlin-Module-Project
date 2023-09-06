import java.util.Scanner

class Menu {
    fun showMenu(
        currentLevel: List<String>,
        currentMenuElements: MutableSet<String>,
        archive: Archive,
        archiveName: String
    ) {
        while (true) {
            println("Выберите или создайте ${currentLevel[2]}:")
            println("0 - Создать ${currentLevel[2]}")
            currentMenuElements.toList().forEachIndexed() { index, element ->
                println("${index + 1} - $element") //создаем список элементов меню
            }
            println("${currentMenuElements.size + 1} - Выход")
            val scanner = Scanner(System.`in`)
            if (!scanner.hasNextInt()) { //проверяем, что введено целое число
                println("Пожалуйста, введите один из указанных пунктов меню.")
                continue
            }
            when (val select = scanner.nextInt()) {
                0 -> { //если выбрано создать элемент
                    if (currentLevel == ARCHIVE) archive.addArchive()
                    if (currentLevel == NOTE) archive.addNote(archiveName)
                }

                in 1..currentMenuElements.toList().size -> { //если выбрано перейти в элемент
                    if (currentLevel == ARCHIVE) archive.gotoNotesMenu(currentMenuElements.toList()[select - 1])
                    if (currentLevel == NOTE) archive.showNote(
                        archiveName,
                        currentMenuElements.toList()[select - 1]
                    )
                }

                (currentMenuElements.size + 1) -> return
                else -> println("Такого пункта меню нет.")
            }
        }
    }
}