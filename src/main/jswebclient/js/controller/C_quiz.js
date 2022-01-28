/**
 * Diese Function nimmt ein Array und mischt diese Durch.
 * @param array Das Array
 * @returns {*[]} Das Array mit einer andren Reihenfolge.
 */
export function mixArray(array){
    let newArray =[];
    for(let i = array.length; i >= array.length; i--){
        if(!i==0){
            let randomNumber = Math.floor(Math.random() * array.length);
            newArray.push(array[randomNumber]);
            array.splice(randomNumber,1);
        }
    }
    return newArray;
}