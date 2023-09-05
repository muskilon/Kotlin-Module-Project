import java.util.Scanner

val ARCHIVE: List<String> = listOf("Архив", "название архива", "архив")
val NOTE: List<String> = listOf("Заметка", "название заметки", "заметку")
val NOTE_TEXT: List<String> = listOf("Текст", "текст заметки")

class Archive(var archive: MutableMap<String, MutableMap<String, String>>) {
    fun addArchive() {
        val archiveTitle = inputString(ARCHIVE)
        if (this.archive.keys.contains(archiveTitle)) println("Архив с таким названием уже есть")
        else if (archiveTitle.isEmpty()) println("В названии архива должен быть хотя бы один символ")
        else this.archive[archiveTitle] = mutableMapOf()
        return
    }

    fun addNote(archiveTitle: String) {
        val noteTitle = inputString(NOTE)
        if (this.archive[archiveTitle]!!.keys.contains(noteTitle)) println("Заметка с таким названием уже есть")
        else if (noteTitle.isEmpty()) println("В названии заметки должен быть хотя бы один символ")
        else {
            while (true) {
                val noteBody = inputString(NOTE_TEXT)
                if (noteBody.isEmpty()) println("Заметка не может быть пустой")
                else {
                    this.archive[archiveTitle]!![noteTitle] = noteBody
                    return
                }
            }
        }
        return
    }

    fun showNote(archiveTitle: String, noteTitle: String) {
        println(this.archive[archiveTitle]!![noteTitle])
        return
    }

    fun gotoNotesMenu(archiveTitle: String) {
        val notesMenu = Menu()
        notesMenu.showMenu(NOTE, this.archive[archiveTitle]!!.keys, this, archiveTitle)
    }
}

fun inputString(type: List<String>): String {
    val scanner = Scanner(System.`in`)
    println("Введите ${type[1]}:")
    return scanner.nextLine()
}

fun main() {
    val archive = Archive(mutableMapOf())
    val archiveMenu = Menu()
    archiveMenu.showMenu(ARCHIVE, archive.archive.keys, archive, "")
}