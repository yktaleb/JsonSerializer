package writers;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class IndentedJsonWriter extends JsonWriter {

    private int indentSize = 4;
    private int currentLevel = 0;

    private List<String> listOfIndents = new ArrayList<String>();

    public IndentedJsonWriter() {

    }

    public IndentedJsonWriter(Writer writer) {
        super(writer);
    }

    public IndentedJsonWriter(Writer writer, int indentSize, int currentLevel) {
        this.writer = writer;
        this.indentSize = indentSize;
        this.currentLevel = currentLevel;
    }

    private String getIndent() throws IOException {
        String indent = null;
        if (listOfIndents.size() == 0) {
            listOfIndents.add("");
        }
        if (currentLevel < listOfIndents.size()) {
            indent = listOfIndents.get(currentLevel);
        }
        if (indent != null) {
            return indent;
        } else {
            StringBuilder stringBuilderIndent = new StringBuilder();
            for (int i = 0; i < indentSize * currentLevel; i++) {
                stringBuilderIndent.append(SPACE);
            }
            String stringIndent = stringBuilderIndent.toString();
            listOfIndents.add(stringIndent);
            return stringIndent;
        }
    }

    private void addIndent() throws IOException {
        writer.write(getIndent());
    }

    private void toNewLine() throws IOException {
        writer.write(NEW_LINE);
        addIndent();
    }

    @Override
    public void writeObjectBegin() throws IOException {
        writer.append(OBJECT_BEGIN);
        currentLevel++;
        toNewLine();
    }

    @Override
    public void writeObjectEnd() throws IOException {
        currentLevel--;
        toNewLine();
        writer.append(OBJECT_END);
    }

    @Override
    public void writeArrayBegin() throws IOException {
        writer.append(ARRAY_BEGIN);
        currentLevel++;
        toNewLine();
    }

    @Override
    public void writeArrayEnd() throws IOException {
        currentLevel--;
        toNewLine();
        writer.append(ARRAY_END);
    }

    @Override
    public void writePropertySeparator() throws IOException {
        writer.append(SPACE).append(PROPERTY_SEPARATOR).write(SPACE);
    }

    @Override
    public void writeSeparator() throws IOException {
        writer.write(SEPARATOR);
        toNewLine();
    }

    public void setWriter(Writer writer) {
        super.writer = writer;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
