package com.day1010.oo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentSerializaton {
	public static void main(String[] args){
		try {
			Student student = new Student();
			student.setAge(11);
			student.setId(2343);
			student.setName("bob");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.output"));
			oos.writeObject(student);
			oos.close();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.output"));
			try {
				Student newStu = (Student) ois.readObject();
				System.out.println(student);
				System.out.println(newStu);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
