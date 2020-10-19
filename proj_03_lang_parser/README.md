# Language parser
Parser tj. jezični procesor trivijalnog programskog jezika, koji ga stavlja u odgovarajuće stablo



Primjer uporabe koda:

String docBody = "PROGRAMSKI KOD";
SmartScriptParser parser = new SmartScriptParser(docBody);
DocumentNode kod = parser.getDocumentNode();

String originalDocumentBody = createOriginalDocumentBody(document); //Vraćanje stabla natrag u string 

SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
DocumentNode kod2 = parser2.getDocumentNode();

//kod i kod2 su strukturno identična stabla
