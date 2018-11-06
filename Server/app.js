var express = require("express");
var router=express.Router();
var mysql = require('mysql');
var bodyParser = require('body-parser');
var urlencodedParser = bodyParser.urlencoded({ extended: false });
var app = express();
app.use('/uploads', express.static('uploads'));
app.use(bodyParser.json());
app.use(router);
app.use(express.static(__dirname + '/'));
var server = require("http").createServer(app);
var io = require("socket.io").listen(server);
var fs = require("fs");
var server1=require("http");
var multiparty = require('multiparty');
var util = require('util');
var http = require('http');
var form = new multiparty.Form();
const multer = require('multer');
const Product = require("./product");
const mongoose = require("mongoose");






server.listen(process.env.PORT || 3000);

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "banhangonline"
});


const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, './uploads')
  },
  filename: function(req, file, cb) {
    cb(null, getMilis() + file.originalname);
  }
});





const fileFilter = (req, file, cb) => {
  // reject a file
  if (file.mimetype === 'image/jpeg' || file.mimetype === 'image/png') {
    cb(null, true);
  } else {
    cb(null, false);
  }
};

const upload = multer({
  storage: storage,
  limits: {
    fileSize: 1024 * 1024 * 5
  },
  fileFilter: fileFilter
});

router.post("/uploadimage", upload.single('productImage'), (req, res, next) => {
   res.status(201).json({
        message: "Created product successfully",
        name: req.file.filename
      });
});






app.post("/getchitietsach",urlencodedParser,function(req,res){
      con.query("SELECT idLS,tenS,tg,nxb,hinhS,mota,giaS,nn,kt,ht,st,giamGia,luotMua,tt FROM sach WHERE idS="+req.body.idS,function(err,result,fields){
        if (err) {
           res.send(err);
        }else{
          res.send(result);
        }
      });
        });

app.post("/getuudai",function(req,res){
      con.query("SELECT * FROM uudai",function(err,result,fields){
        if (err) {
           res.send(err);
        }else{
          res.send(result);
        }
      });
        });


app.post("/search",urlencodedParser,function(req,res){
      con.query("SELECT sach.idS, sach.tenS, sach.giaS, sach.hinhS, sach.giamGia FROM sach WHERE sach.tenS LIKE N'%"+req.body.search+"%'",function(err,result,fields){
        if (err) {
           res.send(err);
        }else{
          res.send(result);
        }
      });
        });




  app.post("/checklogin",urlencodedParser,function(req,res){
      con.query("SELECT * FROM khachhang WHERE userPass = '"+req.body.userPass+"'", function (err, result, fields) {
    if (err) throw err;
       res.send(result);
  });
        });


app.post("/kiemtrataikhoan",urlencodedParser,function(req,res){
      con.query("SELECT * FROM khachhang WHERE user='"+req.body.taikhoan+"'",function(err,result,fields){
        if (err) {
          console.log(err);
        }else{
           if (result.length>0) {
              res.send("0");
           }else{
              res.send("1")
           }
        }
      });
        });

app.post("/insertkhachhang",urlencodedParser,function(req,res){
    
            con.query("INSERT INTO khachhang VALUES(null,"+"'/"+req.body.data+"')",function(err,result,fields){
             if (err) {
              console.log(err);
             }else{
                 res.send("1");
             }
          });
        });

app.post("/getlisthinh",urlencodedParser,function(req,res){
    
            con.query("SELECT hinh FROM hinh WHERE idBaiDang="+req.body.idBaiDang,function(err,result,fields){
    if (err) {
      res.send("0");
    }else{
      res.send(result);
    }
  });
        });


app.post("/insertGroupChat",urlencodedParser,function(req,res){
    
            con.query("INSERT INTO conversation VALUES(null,"+req.body.idCreator+","+req.body.idRecipient+")",function(err,result,fields){
    if (err) {
      res.send(err);
    }else{
      res.send(result);
    }
  });
        });

app.get("/insertnhomchat/:idUser/:idKhachHang",function(req,res){
  var api=req.params.idUser+"_"+req.params.idKhachHang;
  var idUserChat=req.params.idUser;
  var idKHChat=req.params.idKhachHang;
         con.query("SELECT * FROM nhom WHERE idPhong='"+api+"'",function(err,result,fields){
            if (err) {
              console.log(err);
            }else{
              
               if (result.length>0) {
                  res.send("0");
               }else{
                con.query("INSERT INTO nhom VALUES(null,"+idUserChat+","+idKHChat+",'"+api+"')",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
          con.query("INSERT INTO nhom VALUES(null,"+idKHChat+","+idUserChat+",'"+api+"')",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
       res.send("1");
      }
  });
      }
  });
               }
            }
         });
               
        });




app.post("/getLike",function(req,res){
          con.query("SELECT idLike FROM likebaidang",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.send(result);
      }
  });     
        });

app.get("/xoabaidang/:idBaiDang",function(req,res){
          con.query("DELETE FROM baidang WHERE idBaiDang="+req.params.idBaiDang,function(err,result,fields){
    if (err) {
      res.send("0");
    }else{
      res.send("1");
      }
  });     
        });

app.get("/checkroom/:idUser/:idKhachHang",function(req,res){
          con.query("SELECT idPhong FROM nhom WHERE idUser="+req.params.idUser+" AND idKhachHang="+req.params.idKhachHang,function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.send(result);
      }
  });     
        });

app.post("/getLuotLike",urlencodedParser,function(req,res){
   con.query("INSERT INTO likebaidang VALUES('"+req.body.idKhachHang+"-"+req.body.idBaiDang+"',"+req.body.idBaiDang+")",function(err,result,fields){
    if (err) {
      res.send("0");
    }else{
      res.send("1");
    }
   });     
        });



app.post("/getSoBinhLuan",urlencodedParser,function(req,res){
  con.query("SELECT COUNT (*) as 'soBL' FROM binhluan  WHERE idBaiDang ="+req.body.idBD,function(err,result,fields){
    if (err) {
      res.send(err);
    }else{
      res.send(result);
    }
  });
});

app.post("/getSoLuotLike",urlencodedParser,function(req,res){
  con.query("SELECT COUNT (*) as 'soLK' FROM likebaidang  WHERE idBaiDang ="+req.body.idBD,function(err,result,fields){
    if (err) {
      res.send(err);
    }else{
      res.send(result);
    }
  });
});



app.post("/getbaidang",urlencodedParser,function(req,res){
  var space=10;
  var limit=(parseInt(req.body.page)-1)*space;
          con.query("SELECT baidang.idBaiDang,khachhang.idKhachHang,khachhang.sdt,khachhang.ten,khachhang.anhDaiDien,baidang.tenSach,baidang.thoiGian,baidang.noiDung,baidang.gia FROM (baidang INNER JOIN khachhang on baidang.idKhachHang=khachhang.idKhachHang) ORDER BY baidang.idBaiDang DESC LIMIT "+limit+","+space,function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      
      
      res.send(result);
      }
  });     
        });

app.post("/checkLike",urlencodedParser,function(req,res){
          con.query("SELECT COUNT(*) as 'checkLike' FROM likebaidang WHERE idLike='"+req.body.idLike+"'",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.send(result);
      }
  });     
        });


app.post("/getbaidangcanhan",urlencodedParser,function(req,res){
    
            con.query("SELECT baidang.idBaiDang,khachhang.idKhachHang,khachhang.sdt,khachhang.ten,khachhang.anhDaiDien,baidang.tenSach,baidang.thoiGian,baidang.noiDung,baidang.gia FROM (baidang INNER JOIN khachhang on baidang.idKhachHang=khachhang.idKhachHang) WHERE baidang.idKhachHang= "+req.body.idKhachHang+" ORDER BY idBaiDang DESC",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.send(result);
      }
  });     
        });

app.post("/gethinhanh",urlencodedParser,function(req,res){
  con.query("SELECT hinh FROM hinh WHERE idBaiDang="+req.body.idBaiDang,function(err,result,fields){
    if (err) {

    }else{
    
      res.send(result);
    }
  });
});


app.post("/deletenews",urlencodedParser,function(req,res){
  var api=req.body.idBD;

  con.query("DELETE FROM hinh  WHERE  idBaiDang="+api,function(err,result,fields){
    if (err) {
      res.status(200).json({message : {delete:'false',error:'null'}});
    }else{
      con.query("DELETE FROM binhluan  WHERE  idBaiDang="+api,function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      con.query("DELETE FROM likebaidang WHERE idBaiDang="+api,function(err,result,fields){
        if (err) {
          console.log(err);
        }else{
          con.query("DELETE FROM baidang WHERE idBaiDang="+api,function(err,result,fields){
            if (err) {
               console.log(err);
            }else{
              res.status(200).json({message : {delete:'ok',error:'null'}});
            }
          });
           
        }
      });
      
    }
  });
    }
  });
});


app.post("/getnhomchat",urlencodedParser,function(req,res){
    
            con.query("SELECT nhom.idNhom,nhom.idPhong,khachhang.idKhachHang, khachhang.ten,khachhang.anhDaiDien FROM nhom INNER JOIN khachhang on nhom.idKhachHang=khachhang.idKhachHang WHERE nhom.idUser="+req.body.idUser,function(err,result,fields){
             if (err) {
              console.log(err);
             }else{
                 res.send(result);
             }
          });
        });

app.post("/getslbaidang",function(req,res){
    
            con.query("SELECT COUNT (*) as soBL FROM baidang",function(err,result,fields){
             if (err) {
              console.log(err);
             }else{
                 res.send(result);
             }
          });
        });

app.post("/getdanhgia",urlencodedParser,function(req,res){
    
            con.query("SELECT * FROM danhgia WHERE idS="+req.body.idS,function(err,result,fields){
             if (err) {
              console.log(err);
             }else{
                 res.send(result);
             }
          });
        });

app.post("/guidanhgia",urlencodedParser,function(req,res){
    
            con.query("INSERT INTO danhgia VALUES("+req.body.idS+",'"+req.body.danhG+"',"+req.body.giaT+",NOW(),'"+req.body.ten+"')",function(err,result,fields){
             if (err) {
              console.log(err);
             }else{
                 res.send(result);
             }
          });
        });



router.post("/getsach",urlencodedParser,function(req,res){
  
  con.query("SELECT sach.idS, sach.tenS, sach.giaS, sach.hinhS, sach.giamGia FROM sach WHERE idLS="+req.body.idLS+" ORDER BY RAND() limit 10",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.send(result);
    
    }
  });
});

router.post("/getsachmoinhat",urlencodedParser,function(req,res){
  
  con.query("SELECT sach.idS, sach.tenS, sach.giaS, sach.hinhS, sach.giamGia FROM sach WHERE idLS="+req.body.idLS+" ORDER BY sach.idS ASC limit 10",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.send(result);
    
    }
  });
});

router.post("/getDangBai",urlencodedParser,function(req,res){
  
  con.query("INSERT INTO baidang VALUES(null,"+req.body.idKhachHang+",NOW(),'"+req.body.tenSach+"','"+req.body.noiDung+"',"+req.body.giaS+")",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      res.status(200).json({
          message : result,
          error : "null"
      });
    
    }
  });
});





io.sockets.on('connection', function (socket) {

  console.log("NOTICE: NEW USER CONNECTED! " + socket.id);

  socket.on('CLIENT_SEND_REQUEST_LOGIN', function(data){
  	var userAndPass = data;
    socket.un = data;
    console.log(socket.un);
  	checkUserAndPass(userAndPass,socket);
  });
  socket.on('CLIENT_SEND_REQUEST_LOGIN1', function(data){
    var userAndPass = data;
    socket.un = data;
    console.log(socket.un);
    checkUserAndPass1(userAndPass,socket);
  });
  
  socket.on('CLIENT_REQUEST_LIST_HINH',function(data){
    console.log(data);
    getListHinh(socket,data);
  });
  socket.on('CLIENT_REQUEST_LIST_BINH_LUAN',function(data){
    console.log(data);
    getListBinhLuan(socket,data);
  });
  socket.on('CLIENT_SEND_BINHLUAN',function(data){
    console.log(data);
    getSendBinhLuan(socket,data);
  });



    socket.on('CLIENT_SEND_XOA_BINH_LUAN',function(data){
    console.log(data);
    getXoaBinhLuan(socket,data);
  });

  
    socket.on('CLIENT_SEND_BYTE_IMAGE',function(data){
    serverSendResultUpImage(socket,data);
  });

    socket.on('CLIENT_SEND_URL_IMAGE_BAI_DANG',function(data){
      console.log(data);
    getUrlImageBaiDang(socket,data);
  });
    socket.on('CLIENT_SEND_REQUEST_BAIDANG',function(data){
      console.log(data);
    getRequestBaiDang(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_NHOM_CHAT',function(data){
      console.log(data);
    getRequestNhomChat(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_XOA_NHOM_CHAT',function(data){
      console.log(data);
    getRequestXoaNhomChat(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_TIN_NHAN',function(data){
      console.log(data);
    getRequestTinNhan(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_GUI_TIN_NHAN',function(data){
      console.log(data);
    getRequestGuiTinNhan(socket,data);
  });
});






function getRequestGuiTinNhan(socket,data)
{
  var kp=[];
  kp=data.split("-");
  con.query("INSERT INTO message VALUES(null,'"+kp[0]+"',"+kp[1]+",NOW(),'"+kp[2]+"')",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      con.query("SELECT message.idMessage, message.idKhachHang, khachhang.anhDaiDien, message.time, message.content FROM message INNER JOIN khachhang on message.idKhachHang=khachhang.idKhachHang WHERE idPhong='"+kp[0]+"' ORDER BY idMessage ASC",function(err,result,fields){
      if (err) {
        console.log(err);
      }else{
        io.sockets.emit('SERVER_SEND_RESULT_TIN_NHAN',result);
      }
   });
    }
  });
}

function getRequestTinNhan(socket,data)
{
   con.query("SELECT message.idMessage, message.idKhachHang, khachhang.anhDaiDien, message.time, message.content FROM message INNER JOIN khachhang on message.idKhachHang=khachhang.idKhachHang WHERE idPhong='"+data+"' ORDER BY idMessage ASC",function(err,result,fields){
      if (err) {
        console.log(err);
      }else{
        socket.emit('SERVER_SEND_RESULT_TIN_NHAN',result);
      }
   });
}

function getRequestXoaNhomChat(socket,data)
{
   con.query("DELETE FROM nhom WHERE idNhom="+data,function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
        socket.emit('SERVER_SEND_RESULT_XOA_NHOM_CHAT',"1");
    }
   });
}


function getRequestBaiDang(socket,data)
{
    io.sockets.emit('SERVER_SEND_REQUEST_BAIDANG',"1");
}

function getUrlImageBaiDang(socket,data)
{
  var kp=[];
  kp=data.split("-");
  con.query("INSERT INTO hinh VALUES(null,"+kp[0]+",'"+kp[1]+"')",function(err,result,fields){
    if (err) {

    }else{
      socket.emit('SERVER_SEND_RESULT_DANG_BAI',"1");
    }
  });
}

function serverSendResultUpImage(socket,data)
{
  var url = getFileNameImage(socket.id);
  fs.writeFile(url, data, function(err) {
    if(err) {
        return console.log(err);
    }
    socket.emit('SERVER_SEND_RESULT_UP_IMAGE',"/"+url);
  });
}



function getXoaBinhLuan(socket,data)
{
  var kp=[];
  kp=data.split("-");
  con.query("DELETE FROM binhluan WHERE idBinhLuan="+kp[0]+" AND idKhachHang="+kp[1],function(err,result,fields){
    if(err){
      console.log(err);
    }else{
      var kpX=JSON.stringify(result);
      kpX=kpX.substring(31,32);
      socket.emit('SERVER_SEND_RESULT_XOA_BINH_LUAN',kpX);
      getListBinhLuan(socket,kp[2]);
    }
  });
}



function getSendBinhLuan(socket,data){
  var kp=[];
  kp=data.split(",");
  con.query("INSERT INTO binhluan VALUES (null,"+kp[0]+","+kp[1]+",'"+kp[2]+"',NOW())",function(err,result,fields){
    if (err) {

    }else{
       con.query("SELECT binhluan.idBinhLuan,khachhang.idKhachHang,khachhang.anhDaiDien,khachhang.ten,noiDung,binhluan.thoiGian FROM binhluan INNER JOIN khachhang on binhluan.idKhachHang=khachhang.idKhachHang WHERE idBaiDang="+kp[0]+" ORDER BY idBinhLuan DESC",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{

      io.sockets.emit('SERVER_SEND_RESULT_BINH_LUAN',result);
    }
  });
    }
  });
}

function getListBinhLuan(socket,data){
  con.query("SELECT binhluan.idBinhLuan,khachhang.idKhachHang,khachhang.anhDaiDien,khachhang.ten,binhluan.noiDung,binhluan.thoiGian FROM binhluan INNER JOIN khachhang on binhluan.idKhachHang=khachhang.idKhachHang WHERE idBaiDang="+data+" ORDER BY idBinhLuan DESC",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{  
      console.log(result);
      socket.emit('SERVER_SEND_RESULT_BINH_LUAN',result);
    }
  });
}

function  getListHinh(socket,data)
{
  con.query("SELECT hinh FROM hinh WHERE idBaiDang="+data,function(err,result,fields){
    if (err) {

    }else{
      
      socket.emit('SERVER_SEND_RESULT_HINH',result);
    }
  });
}


function checkUserAndPass(userPass,socket)
{
  con.query("SELECT * FROM khachhang WHERE userPass = '"+userPass+"'", function (err, result, fields) {
    if (err) throw err;
    socket.emit('SERVER_SEND_RESULT',result);
  
  });
	//console.log(userPass);
}
function checkUserAndPass1(userPass,socket)
{
  con.query("SELECT * FROM khachhang WHERE userPass = '"+userPass+"'", function (err, result, fields) {
    if (err) throw err;
    socket.emit('SERVER_SEND_RESULT1',result);
  

  });
  //console.log(userPass);
}





function getFileNameImage(id)
{
  return "image/baidang/" + id.substring(2) + getMilis() + ".jpg";
}

function getFileNameImageKhachHang(id)
{
  return "image/khachhang/" + id.substring(2) + getMilis() + ".jpg";
}

function getFileNameImageTinNhan(id)
{
  return "image/tinnhan/" + id.substring(2) + getMilis() + ".jpg";
}



function getFileNameImageBinhLuan(id)
{
  return "image/binhluan/" + id.substring(2) + getMilis() + ".jpg";
}



function getMilis()
{
  var date = new Date();
  var milis = date.getTime();
  return milis;
}





