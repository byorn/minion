/** @Author: Byorn **/

/** Config Area **/
//======================================================================================//
def entityName = "Flyer"
def packageNameStart = "com.eblaster"
//def templatePath = "C:\\work\\Projects\\minion\\build\\resources\\main\\templates\\"
//def targetPath = "C:\\work\\Projects\\minion\\target\\"

def templatePath = "/home/byorn/Projects/minion/build/resources/main/templates/"
def targetPath = "/home/byorn/Projects/minion/target/"


def lineReplacer = {   
                line ->
                def  str  =   line.replace("{Entity}",entityName)
                .replace("{entity}",entityName.replaceFirst (entityName.substring(0,1), entityName.substring(0,1).toLowerCase()))
                                  .replace("{PackageNameStart}",packageNameStart)
                return str
 }

def templatesAndTargets = [
     "TemplateController.java" :  entityName + "Controller.java",
     "TemplateDAO.java" : entityName + "DAO.java",
     "TemplateSearchDTO.java" : "Search"+entityName+"DTO.java",
     "template_edit.xhtml" : "edit.xhtml",
     "template_new.xhtml" : "new.xhtml" ,
     "template_search.xhtml" : "search.xhtml"
]

//======================================================================================//

templatesAndTargets.each{ template, target ->
    
    Generator.generateFile lineReplacer, templatePath + template, targetPath  +  target
}

