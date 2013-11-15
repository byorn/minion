/** @Author: Byorn **/

/** Config Area **/
//======================================================================================//
def entityName = "Role"
def packageNameStart = "Company1.Company"
def templatePath = "C:\\work\\Projects\\minion\\build\\resources\\main\\templates\\"
def targetPath = "C:\\work\\Projects\\minion\\target\\"
def lineReplacer = {   
                line ->
                def  str  =   line.replace("{Entity}",entityName)
                                  .replace("{PackageNameStart}",packageNameStart)
                return str;
 }

def templatesAndTargets = [
     "TemplateController.java" : "Controller.java",
     "TemplateDAO.java" : "DAO.java",
     "TemplateSearchDTO.java" : "SearchDTO.java",
     "template_edit.xhtml" : "edit.xhtml",
     "template_new.xhtml" : "new.xhtml" ,
     "template_search.xhtml" : "search.xhtml"
]

//======================================================================================//

templatesAndTargets.each{ template, target ->
    
    Generator.generateFile lineReplacer, templatePath + template, targetPath + entityName +  target
}

