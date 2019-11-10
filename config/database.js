module.exports=(function(){
    
    return{
        local:{
            host:'localhost',
            user:'root',
            password:'12345678',
            port:3306,  
            database:'test_db'
        },
        
        real:{
            host:'',
            user:'',
            password:'',
            database:'test_db'
        },
        dev:{
            host:'',
            user:'',
            password:'',
            database:'test_db'
        }
    }
})();
