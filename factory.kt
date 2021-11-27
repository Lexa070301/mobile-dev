/*
Фабричный метод — это порождающий паттерн проектирования, определяющий общий интерфейс для создания объектов, позволяющий подклассам изменять тип создаваемых объектов.
 */

// интерфейс документа
interface Document {
    fun showDocumentInfo()
}

// Класс рисунка
class DrawingDocument: Document {
    override fun showDocumentInfo() {
        println("This is a drawing document")
    }
}

// Класс текстового документа
class WordDocument: Document {
    override fun showDocumentInfo() {
        println("This is a word document")
    }
}

// Абстрактный класс приложения
abstract class Application {
    abstract fun createDocument(): Document

    companion object {
        fun getApplicationDocument(documentType: DocumentType): Application {
            return when (documentType) {
                DocumentType.Drawing -> DrawingApplication()
                DocumentType.Word -> WordApplication()
                else -> throw Exception("Invalid document type")
            }
        }
    }
}


// Класс приложения для рисунков
class DrawingApplication: Application() {
    override fun createDocument() = DrawingDocument()
}

// Класс приложения для текстовых документов
class WordApplication: Application() {
    override fun createDocument() = WordDocument()
}

enum class DocumentType { Drawing, Word }


// Благодаря паттерну фабрики мы можем создавать сколько угодно разных типов документов и приложений
fun main() {
    val drawingApplication = Application.getApplicationDocument(DocumentType.Drawing)
    val drawingDocument = drawingApplication.createDocument()
    drawingDocument.showDocumentInfo()

    val wordApplication = Application.getApplicationDocument(DocumentType.Word)
    val wordDocument = wordApplication.createDocument()
    wordDocument.showDocumentInfo()
}
