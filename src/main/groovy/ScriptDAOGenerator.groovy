/**
 * Created with IntelliJ IDEA.
 * User: byorn
 * Date: 3/11/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
import groovy.sql.Sql


//Replace these values
 def replacer = {   
                line ->
                    line.replace("{Entity}","Privilege")
                        .replace("{PackageNameStart}","Company.Company")
                return line;
 }
                
def template_dao = "C:\\work\\Projects\\minion\\build\\resources\\main\\templates\\TemplateDAO.java"
def file_to_create = "C:\\work\\Projects\\minion\\build\\TestDAO.java"







def newfile = new File(file_to_create)
newfile.createNewFile();
def file = new File(template_dao)
file.eachLine{
        line ->
        def str = 
       
           

        newfile.append(str + "\n")
}

println "done"

