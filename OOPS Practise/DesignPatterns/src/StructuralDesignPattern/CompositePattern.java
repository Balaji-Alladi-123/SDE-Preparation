package StructuralDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface FileSystemComponent {
    void displayDetails();
}
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }
    @Override
    public void displayDetails() {
        System.out.println("Name of File: " + name);
    }
}

class BalajiDirectory implements FileSystemComponent {
    String fileName;
    List<FileSystemComponent> fileSystemComponentList = new ArrayList<>();

    BalajiDirectory(String fileName) {
        this.fileName = fileName;
    }

    void addFile(FileSystemComponent fileSystemComponent) {
        fileSystemComponentList.add(fileSystemComponent);
    }

    void removeFile(FileSystemComponent fileSystemComponent) {
        fileSystemComponentList.remove(fileSystemComponent);
    }

    @Override
    public void displayDetails() {
        System.out.println("Name of File: " + fileName);
        for(FileSystemComponent file : fileSystemComponentList) {
            file.displayDetails();
        }
    }
}

class FileDirectoryLocal implements FileSystemComponent {
    String fileName;
    List<FileSystemComponent> fileSystemComponentList = new ArrayList<>();

    FileDirectoryLocal(String fileName) {
        this.fileName = fileName;
    }

    void addFile(FileSystemComponent fileSystemComponent) {
        fileSystemComponentList.add(fileSystemComponent);
    }

    void removeFile(FileSystemComponent fileSystemComponent) {
        fileSystemComponentList.remove(fileSystemComponent);
    }

    @Override
    public void displayDetails() {
        System.out.println("Name of File: " + fileName);
        for(FileSystemComponent file : fileSystemComponentList) {
             file.displayDetails();
        }
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        FileSystemComponent fileSystemComponent1 = new File("ProjectFile.docx");
        FileSystemComponent fileSystemComponent2 = new File("Caminon.pdf");

        BalajiDirectory balajiDirectory = new BalajiDirectory("Document");
        balajiDirectory.addFile(fileSystemComponent1);
        balajiDirectory.addFile(fileSystemComponent2);
        balajiDirectory.displayDetails();
    }
}
