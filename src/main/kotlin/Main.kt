import java.util.Scanner

val ARCHIVE: List<String> = listOf("Название архива", "название архива", "архив")
val NOTE: List<String> = listOf("Название заметки", "название заметки", "заметку")
val NOTE_TEXT: List<String> = listOf("Текст", "текст заметки")

fun inputString(type: List<String>, archive: Archive, archiveTitle: String): Pair<String, Boolean> {
    val scanner = Scanner(System.`in`)
    println("Введите ${type[1]}:")
    val result = scanner.nextLine()
    if(result.isEmpty()){
        println("${type[0]} не может быть пустым")
        return Pair(result, true)
    }
    when (type){
        ARCHIVE -> {
            if (archive.archive.keys.contains(result)) {
                println("Архив с таким названием уже есть")
                return Pair(result, true)
            }
        }
        NOTE -> {
            if (archive.archive[archiveTitle]!!.keys.contains(result)) {
                println("Заметка с таким названием уже есть")
                return Pair(result, true)
            }
        }
    }
    return Pair(result, false)
}
class Archive(var archive: MutableMap<String, MutableMap<String, String>>) {
    fun addArchive() {
        val archiveTitle = inputString(ARCHIVE,  this, "")
        if (archiveTitle.second) return
        else this.archive[archiveTitle.first] = mutableMapOf()
        return
    }

    fun addNote(archiveTitle: String) {
        val noteTitle = inputString(NOTE, this, archiveTitle)
        if (noteTitle.second) return
        else {
            while (true) {
                val noteBody = inputString(NOTE_TEXT, this, archiveTitle)
                if (!noteBody.second){
                    this.archive[archiveTitle]!![noteTitle.first] = noteBody.first
                    return
                }
            }
        }
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

fun main() {
    val archive = Archive(mutableMapOf())
    val archiveMenu = Menu()
    archiveMenu.showMenu(ARCHIVE, archive.archive.keys, archive, "")
}