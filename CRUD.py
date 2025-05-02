import Mysql.connector
from tabulate import tabulate

con = mysql.connector.connect(
    host="localhost",
    user="root",
    password="1234567890",
    database="datas"

)

class student_management_system():
    def insert(self, NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY):
        res = con.cursor()
        sql = "INSERT INTO STUDENT_MANAGEMENT (NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
        user = (NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY)
        res.execute(sql, user)
        con.commit()
        res.close()
        print("Data successfully inserted")

    def update(self, ID_NO, NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY):
        res = con.cursor()
        sql = "UPDATE STUDENT_MANAGEMENT SET NAME=%s, AGE=%s, GENDER=%s, DEPARTMENT=%s, COUNTRY_CODE=%s, CONTACT=%s, CITY=%s, STATE=%s, COUNTRY=%s WHERE ID_NO=%s"
        user = (NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY, ID_NO)
        res.execute(sql, user)
        con.commit()
        res.close()
        print("Data successfully updated")

    def select(self):
        res = con.cursor()
        sql = "SELECT * FROM STUDENT_MANAGEMENT"
        res.execute(sql)
        result = res.fetchall()
        print(tabulate(result, headers=["ID_NO", "NAME", "AGE", "GENDER", "DEPARTMENT", "COUNTRY_CODE", "CONTACT", "CITY", "STATE", "COUNTRY"]))
        res.close()

    def delete(self, ID_NO):
        res = con.cursor()
        sql = "DELETE FROM STUDENT_MANAGEMENT WHERE ID_NO=%s"
        res.execute(sql, (ID_NO,))
        con.commit()
        res.close()
        print("Data successfully deleted")

call = student_management_system()

while True:
    print("INSERT FOR PRESS 1")
    print("UPDATE FOR PRESS 2")
    print("SELECT FOR PRESS 3")
    print("DELETE FOR PRESS 4")
    print("END FOR PRESS 5")

    choice = int(input("enter your choice:"))

    if choice == 1:
        NAME = input("enter the name:")
        AGE = int(input("enter the age:"))
        GENDER = input("enter the gender:")
        DEPARTMENT = input("enter your department:")
        COUNTRY_CODE = input("enter your country code:")
        CONTACT = int(input("enter your contact number:"))
        CITY = input("enter your city:")
        STATE = input("enter your state:")
        COUNTRY = input("enter your country:")
        call.insert(NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY)

    elif choice == 2:
        ID_NO = int(input("enter the ID_NO:"))
        NAME = input("enter the name:")
        AGE = int(input("enter the age:"))
        GENDER = input("enter the gender:")
        DEPARTMENT = input("enter your department:")
        COUNTRY_CODE = input("enter your country code:")
        CONTACT = int(input("enter your contact number:"))
        CITY = input("enter your city:")
        STATE = input("enter your state:")
        COUNTRY = input("enter your country:")
        call.update(ID_NO, NAME, AGE, GENDER, DEPARTMENT, COUNTRY_CODE, CONTACT, CITY, STATE, COUNTRY)

    elif choice == 3:
        call.select()

    elif choice == 4:
        ID_NO = int(input("enter your ID:"))
        call.delete(ID_NO)

    elif choice == 5:
        break

    else:
        print("please enter a valid input")