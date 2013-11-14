/**
 * Created with IntelliJ IDEA.
 * User: byorn
 * Date: 3/11/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
import groovy.sql.Sql

def entity = "Privilege"
def packageName = "com.softwareperson.dao"
def searchfield_getter = "Name"
def searchfield = "name"


def template_dao = "C:\\work\\Projects\\minion\\build\\resources\\main\\templates\\TemplateDAO.java"
def file_to_create = "C:\\work\\Projects\\minion\\build\\"+entity+"DAO.java"



//def sql = Sql.newInstance('jdbc:mysql://localhost:3306/sambol', 'root', '', 'com.mysql.jdbc.Driver')
//sql.eachRow('select * from user'){ row ->
//  //  println row[0]
//}

def newfile = new File(file_to_create)
newfile.createNewFile();
def file = new File(template_dao)
file.eachLine{
        line->
        def str = line.replace("{entity}",entity)
                .replace("{packageName}",packageName)
                .replace("{searchfield_getter}",searchfield_getter)
                .replace("{searchfield}",searchfield)

        newfile.append(str + "\n")
}

println "done"

