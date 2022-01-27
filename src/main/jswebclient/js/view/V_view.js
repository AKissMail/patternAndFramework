/**
 * Diese Function erstellt ein Div mit Text.
 * @param id Die iD des element
 * @param text Der Test im element
 * @param cssClass Die Klasse des
 * @returns {HTMLDivElement}
 *
 */
export function createButton (id, text, cssClass){
    let span = document.createElement('div');
    let p = document.createElement('p');
    span.setAttribute('id', id);
    span.setAttribute('class', cssClass);
    p.append(text);
    span.appendChild(p);
    return span;
}

/**
 * Diese Function erstellt ein beliebiges Element mit einer id einer Klasse und in dies Element befinden sich ein Bild
 * und ein Text.
 * @returns {*} Das oben beschriebene Element
 * @param wType Type des Mutterelementes
 * @param wId id des Mutterelementes
 * @param wCssClass Klasse des Mutterelementes
 * @param mType Type des Bildes
 * @param mId  id des Bildes
 * @param mSrc Quelle des Bildes
 * @param cType Type des Textes
 * @param cId id des Textes
 * @param cText inhalt des Textes
 */
export function createAside (wType, wId, wCssClass,mType, mId, mSrc, cType, cId,cText){
    let wrapper = document.createElement(wType);
    wrapper.setAttribute('id', wId);
    wrapper.setAttribute('class',wCssClass);
    let mainContent = document.createElement(mType);
    mainContent.setAttribute('id', mId);
    mainContent.setAttribute('src', mSrc);
    wrapper.appendChild(mainContent);
    let contentDescription = document.createElement(cType);
    contentDescription.setAttribute('id', cId);
    contentDescription.append(cText);
    wrapper.appendChild(contentDescription);
    return wrapper;
}

/**
 * Diese Function erstellt einen Input mit Attribute
 * @param tagName Typ des Tags
 * @param type wert des Attributes type
 * @param name wert des Attributes name
 * @param value wert des Attributes value
 * @param placeholder wert des Attributes placeholder
 * @param id wert des Attributes id
 * @returns {*} den Input
 */
export function createInput(tagName, type, name, value, placeholder, id){
    let input = document.createElement(tagName);
    input.setAttribute("type", type);
    input.setAttribute("name", name);
    input.setAttribute("value", value);
    input.setAttribute("placeholder", placeholder);
    input.setAttribute("id", id);
    return input;
}

/**
 * Diese Function erstellt ein beliebiges Dom-Element mit Klasse und ID.
 * @param tagName type des Elements
 * @param cssClass klasse des Elements
 * @param id id des Elements
 * @returns {*} Das Dom-Element
 */
export function createGenericElementWithClassAndID(tagName, cssClass, id){
    let element = document.createElement(tagName);
    element.setAttribute("class", cssClass);
    element.setAttribute("id", id);
    return element;
}

/**
 * Diese Function erstellt ein Text
 * @param tagName type des Textes (h1 etc)
 * @param text Inhalt des Texts
 * @returns {*} Das Dom-Element
 */
export function createGenericText(tagName,text){
    let t = document.createElement(tagName);
    t.append(text);
    return t;
}

/**
 *
 * @param tagName
 * @param attribute
 * @param attributeValue
 * @returns {*}Das Dom-Element
 */
export function createGenericElementWithOneAttribute (tagName,attribute,attributeValue) {
    let element = document.createElement(tagName);
    element.setAttribute(attribute, attributeValue);
    return element;
}
export function createGenericElementWithTwoAttribute (tagName,attributeOne,attributeOneValue, attributeTwo,attributeTwoValue) {
    let element = document.createElement(tagName);
    element.setAttribute(attributeOne, attributeOneValue);
    element.setAttribute(attributeTwo, attributeTwoValue);
    return element;
}

/**
 * Diese Function erstellt ein Teil eines Formulates
 * @param attributeValue Wert des Elements
 * @param text Bezeichnung des Element
 * @returns {HTMLOptionElement}Das Dom-Element
 */
export function createOption(attributeValue, text) {
    let option = document.createElement("option",);
    option.setAttribute("value", attributeValue);
    option.append(text);
    return option;
}

/**
 * Diese Function erstellt ein brake.
 * @returns {HTMLBRElement}Das Dom-Element
 */
export function createBarak(){
    return document.createElement("br");
}

/**
 * @todo
 * @returns {HTMLButtonElement}
 */
export function createQuestionButton (){
    return document.createElement("button");
}