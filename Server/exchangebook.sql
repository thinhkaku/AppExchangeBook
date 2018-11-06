-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 06, 2018 lúc 03:19 PM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `banhangonline`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baidang`
--

CREATE TABLE `baidang` (
  `idBaiDang` int(11) NOT NULL,
  `idKhachHang` int(11) NOT NULL,
  `thoiGian` datetime NOT NULL,
  `tenSach` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `noiDung` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gia` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `baidang`
--

INSERT INTO `baidang` (`idBaiDang`, `idKhachHang`, `thoiGian`, `tenSach`, `noiDung`, `gia`) VALUES
(3, 1, '2018-03-31 00:00:00', 'Sách bitcoit', 'thihnaf-akfdfsd', 300000),
(4, 1, '2018-03-31 00:00:00', 'Đắc nhân tâm', 'Đắc nhân tâm – How to Win Friends & Influence People của Dale Carnegie là một cuốn sách nổi tiếng có tầm ảnh hưởng nhất mọi thời đại. Gần 80 năm kể từ khi ra đời, Đắc Nhân Tâm là cuốn sách gối đầu giường của nhiều thế hệ luôn muốn hoàn thiện chính mình để vươn tới một cuộc sống thành công và tốt đẹp.', 300000),
(51, 1, '2018-04-08 23:15:34', 'kiếm hiệp', 'xin chào các bạn', 50000),
(53, 1, '2018-04-08 23:49:41', 'vui thôi đừng vui quá', 'ừ thì không vui', 99999),
(56, 2, '2018-04-09 14:28:27', 'vớ vẩn', 'không có gì để nói', 500000),
(57, 1, '2018-04-13 21:59:09', 'test', 'test', 50000),
(66, 2, '2018-04-23 20:45:10', 'test', 'shsjsjsj', 646644),
(87, 1, '2018-04-25 23:38:39', 'test xoa bai', 'helllo', 55555),
(88, 3, '2018-04-26 02:14:25', 'hey', 'ồ', 6464646),
(89, 1, '2018-04-29 20:40:35', 'ttt', '3', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `binhluan`
--

CREATE TABLE `binhluan` (
  `idBinhLuan` int(11) NOT NULL,
  `idBaiDang` int(11) NOT NULL,
  `idKhachHang` int(11) NOT NULL,
  `noiDung` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `thoiGian` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `binhluan`
--

INSERT INTO `binhluan` (`idBinhLuan`, `idBaiDang`, `idKhachHang`, `noiDung`, `thoiGian`) VALUES
(132, 53, 2, 'hey&1', '2018-05-06 16:03:16'),
(133, 56, 2, 'hey&1', '2018-05-06 16:10:16'),
(135, 4, 3, 'hey&image/binhluan/K5DmMJMnTLdNnVAAAF1525600025675.jpg', '2018-05-06 16:47:05');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhgia`
--

CREATE TABLE `danhgia` (
  `idS` int(11) NOT NULL,
  `danhG` text COLLATE utf8_unicode_ci NOT NULL,
  `giaT` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `ten` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `danhgia`
--

INSERT INTO `danhgia` (`idS`, `danhG`, `giaT`, `time`, `ten`) VALUES
(4, 'ok', 4, '2018-11-06 20:07:14', 'thinh'),
(1, 'ok', 4, '2018-11-06 20:08:18', 'thinh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hinh`
--

CREATE TABLE `hinh` (
  `idHinh` int(11) NOT NULL,
  `idBaiDang` int(11) NOT NULL,
  `hinh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `hinh`
--

INSERT INTO `hinh` (`idHinh`, `idBaiDang`, `hinh`) VALUES
(4, 4, '/image/baidang/dacnhantam.jpg'),
(5, 4, '/image/baidang/dacnhantam1.jpg'),
(6, 4, '/image/baidang/dacnhantam2.jpg'),
(7, 4, '/image/baidang/dacnhantam3.jpg'),
(18, 51, '/image/baidang/U831EHWKaMDrYNAAAD1523204157154.png'),
(19, 51, '/image/baidang/U831EHWKaMDrYNAAAD1523204157517.png'),
(26, 53, '/image/baidang/5MzlDOKGXQmKvLAAAB1523206260861.png'),
(27, 53, '/image/baidang/5MzlDOKGXQmKvLAAAB1523206261004.png'),
(28, 53, '/image/baidang/5MzlDOKGXQmKvLAAAB1523206261317.png'),
(29, 53, '/image/baidang/5MzlDOKGXQmKvLAAAB1523206261541.png'),
(30, 53, '/image/baidang/5MzlDOKGXQmKvLAAAB1523206261806.png'),
(37, 56, '/image/baidang/98MJPief1iVD8xAAAL1523258962424.png'),
(38, 56, '/image/baidang/98MJPief1iVD8xAAAL1523258962697.png'),
(39, 56, '/image/baidang/98MJPief1iVD8xAAAL1523258963063.png'),
(55, 66, '/image/baidang/GjB2ix2L05vESZAAAJ1524491143988.png'),
(58, 88, '/image/baidang/TyyOrlgxR8kWHNAAAJ1524683693766.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `idKhachHang` int(11) NOT NULL,
  `anhDaiDien` varchar(100) NOT NULL,
  `ten` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(25) DEFAULT NULL,
  `sdt` int(11) NOT NULL,
  `diaChi` varchar(50) NOT NULL,
  `userPass` varchar(20) NOT NULL,
  `user` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`idKhachHang`, `anhDaiDien`, `ten`, `email`, `sdt`, `diaChi`, `userPass`, `user`) VALUES
(1, '/image/khachhang/thinhkaku.jpg', 'Thịnh kaku', 'thinhkaku.a3k51@gmail.com', 971129897, 'Hà Nam', 'thinh-1', 'thinh'),
(2, '/image/khachhang/ngocthinh.jpg', 'Ngọc Thịnh', 'ngocthinh@gmail.com', 1564486484, 'Hà Nam', 'ngocthinh-1', 'ngocthinh'),
(3, '/image/khachhang/hieu.jpg', 'hiếu', 'heisifdsfsdf', 5645665, 'hà nam', 'hieu-1', 'hieu'),
(11, '/image/khachhang/KdPxYh10tmc1zcAAAH1525163742147.png', 'thinhkaku ', 'thinhkaku.a3k51@gmail.com', 9, 'hà nsm', 'thinhkaku-123456', 'thinhkaku'),
(14, '/image/khachhang/CCTchV61sLFTQiAAAC1526802672588.jpg', 'gh', 'example@gmail.com', 66, 'y', 'thinhg-h', 'thinhg;thinhg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `likebaidang`
--

CREATE TABLE `likebaidang` (
  `idLike` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `idBaiDang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `likebaidang`
--

INSERT INTO `likebaidang` (`idLike`, `idBaiDang`) VALUES
('1-88', 88),
('11-89', 89),
('2-56', 56),
('3-56', 56),
('3-88', 88);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisach`
--

CREATE TABLE `loaisach` (
  `idLS` int(11) NOT NULL,
  `tenLoaiSach` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `loaisach`
--

INSERT INTO `loaisach` (`idLS`, `tenLoaiSach`) VALUES
(1, 'Sách văn học'),
(2, 'Sách kinh tế'),
(3, 'Sách tâm lý'),
(4, 'Truyện tranh'),
(5, 'Tuổi teen'),
(6, 'Nuôi dạy trẻ'),
(7, 'Khoa học '),
(8, 'Nước ngoài');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `message`
--

CREATE TABLE `message` (
  `idMessage` int(11) NOT NULL,
  `idPhong` varchar(11) NOT NULL,
  `idKhachHang` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `message`
--

INSERT INTO `message` (`idMessage`, `idPhong`, `idKhachHang`, `time`, `content`) VALUES
(118, '1_2', 1, '2018-05-06 10:09:03', 'hey'),
(119, '1_2', 2, '2018-05-06 10:09:23', '????'),
(120, '2_3', 3, '2018-05-06 16:45:37', 'hey'),
(121, '2_3', 3, '2018-05-06 16:45:50', 'image/tinnhan/K5DmMJMnTLdNnVAAAF1525599950201.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhom`
--

CREATE TABLE `nhom` (
  `idNhom` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idKhachHang` int(11) NOT NULL,
  `idPhong` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `nhom`
--

INSERT INTO `nhom` (`idNhom`, `idUser`, `idKhachHang`, `idPhong`) VALUES
(78, 1, 2, '1_2'),
(79, 2, 1, '1_2'),
(80, 2, 3, '2_3'),
(81, 3, 2, '2_3'),
(82, 1, 11, '1_11'),
(83, 11, 1, '1_11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `idS` int(11) NOT NULL,
  `idLS` int(11) NOT NULL,
  `tenS` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tg` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nxb` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hinhS` varchar(200) NOT NULL,
  `mota` varchar(3000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `giaS` int(6) NOT NULL,
  `nn` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `kt` varchar(20) NOT NULL,
  `ht` tinyint(1) NOT NULL,
  `st` int(6) NOT NULL,
  `giamGia` int(5) NOT NULL,
  `luotMua` int(11) NOT NULL,
  `tt` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`idS`, `idLS`, `tenS`, `tg`, `nxb`, `hinhS`, `mota`, `giaS`, `nn`, `kt`, `ht`, `st`, `giamGia`, `luotMua`, `tt`) VALUES
(1, 1, 'Date A Live - Tập 1 - Quà Tặng Giá Đỡ Điện Thoại Độc Quyền (Số Lượng Có Hạn)', 'Tachibana Koushi', 'NXB Văn Học', 'https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/d/a/dateeee.png', 'Date A Live - Tập 1 - Quà Tặng Giá Đỡ Điện Thoại Độc Quyền (Số Lượng Có Hạn)\r\n\r\nNgày 10 tháng 4.\r\n\r\nNgày đầu tiên đi học sau kỳ nghỉ xuân, Itsuka Shido được em gái dễ thương của mình đánh thức như mọi khi. Nhưng cậu không biết rằng hôm nay là ngày mà định mệnh cậu thay đổi chỉ bởi một cuộc gặp gỡ tình cờ với một cô gái vô danh......\r\nCùng với một cơn địa chấn đột ngột, một phần của thị trấn đã biến mất mà không để lại dấu vết gì. Tại góc thành phố đã lún sâu thành hố khổng lồ xuất hiện một thiếu nữ.\r\n\"Cậu cũng đến để giết tôi ư?\"\r\nCô ấy là tai họa sẽ hủy diệt nhân loại, là một con quái vật không rõ nguồn gốc, và là một sinh linh bị ruồng bỏ. Chỉ có hai cách để ngăn chặn cô gái này: tiêu diệt hoặc nói chuyện. \r\n\"Vì thế, hãy hẹn hò và khiến cô ta yêu anh đi!\"\r\nNhờ sự giúp đỡ của Kotori, em gái mình, Shido sẽ giải cứu cô gái ấy và cả nhân loại bằng cách sử dụng \"mỹ nam kế\" - làm cô yêu cậu say đắm??!!! Là phúc hay họa đây?!! \r\nMột thế giới mới cùng những mối quan hệ lãng mạn xin được phép bắt đầu!', 52500, 'Sách tiếng việt', '13 x 18', 1, 350, 10, 0, 1),
(2, 1, 'Hiệp Sĩ Lưu Ban - Tập 4 - Tặng Kèm Bookmark Plastic (Số Lượng Có Hạn)', 'Misora Riku', 'NXB Văn Học', 'https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/a/mau-lam-bia-sach_2__9_1.png', 'Hiệp Sĩ Lưu Ban - Tập 4 - Tặng Kèm Bookmark Plastic (Số Lượng Có Hạn)\r\n\r\nĐoàn tuyển thủ đại diện của học viện Hagun, Ikki và các bạn đang chuẩn bị cho Thất tinh võ hội. Đợt tập huấn chung với học viện Kyomon, học viện đứng đầu của vùng Đông Bắc cũng kết thúc viên mãn. Nhưng vừa tưởng mọi việc đã ổn thỏa thì học viện Hagun đột nhiên bị tập kích.\r\n \r\n“CHÚNG TA SẼ ĐÁNH BẠI CÁC NGƯƠI TẠI ĐÂY ĐỂ LÀM BÀN ĐẠP THAM DỰ VÕ HỘI!”\r\n \r\nIkki và đội tinh nhuệ của hội học sinh phải đối đầu với Akatsuki và đồng bọn, những Blazer có thực lực của thế giới ngầm. Ngay khi cuộc chiến vừa bắt đầu thì “cậu ta”, người đáng lẽ là đồng bọn của chúng lại để lộ nanh vuốt được ẩn giấu! Sau đó, người mà Ikki phải đối đầu lại là... kẻ mạnh nhất thế giới! Cuộc gặp gỡ quá sớm này rốt cuộc sẽ ra sao...!? Đừng bỏ lỡ tập 4 với những trận đấu quyết liệt giữa lý tưởng và hiện thực, hy vọng và tuyệt vọng này nhé!', 68000, 'Tiếng Việt', '13x18', 1, 345, 20, 0, 0),
(3, 1, 'Thanh Xuân Của Chúng Ta Sẽ Kéo Dài Bao Lâu? - Bản Đặc Biệt - Tặng Kèm Chữ Ký Tác Giả + 5 Poscard (Số Lượng Có Hạn)', 'Thảo Thảo', 'NXB Văn Học', 'https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/a/mau-lam-bia-sach_2__1_2_1.png', 'Thanh Xuân Của Chúng Ta Sẽ Kéo Dài Bao Lâu? - Bản Đặc Biệt - Tặng Kèm Chữ Ký Tác Giả + 5 Poscard (Số Lượng Có Hạn)\r\n\r\nTHANH XUÂN CỦA CHÚNG TA SẼ KÉO DÀI BAO LÂU?\r\n\r\nEM KHÔNG BIẾT, NHƯNG CHỈ CẦN CÓ ANH, MỘT PHÚT THÔI CŨNG ĐỦ!\r\n\r\n “Điều đáng tiếc nhất của em, đó là dành hết cả thanh xuân, tình yêu, niềm tin của mình cho một người không xứng đáng.\r\n\r\nĐiều đáng tiếc nhất của anh, đó là chỉ vì những vật chất hào nhoáng, những giây phút giây mù quáng, sa ngã mà đánh mất em.\r\n\r\nĐiều đáng tiếc nhất của chúng ta, là đã quá nông cạn và trẻ con mà để lỡ mất nhau trong muôn vàn đớn đau và nuối tiếc.\r\n\r\nNhưng nếu thời gian có quay trở lại, em vẫn sẽ chọn yêu anh, dù cho chuyện tình này chẳng hề vui vẻ, nhưng em biết, những năm tháng bên nhau, chúng ta đã sống và yêu nhau bằng một tình yêu chân thành nhất, mà dù chọn người khác, em chưa chắc đã có được.\r\n\r\nNếu được gọi tên thanh xuân của mình, em sẽ đặt tên là ANH!”\r\n\r\n Người ta vẫn thường nói thanh xuân là quãng thời gian đầy sóng gió bởi vì lúc đấy chúng ta không biết câu trả lời là gì. Chúng ta không biết thật sự bản thân muốn gì, ai thật lòng yêu chúng ta và chúng ta thật lòng yêu ai. Đó là quãng thời gian chúng ta cứ quẩn quanh đây đó để tìm kiếm câu trả lời. Và rồi khi thanh xuân đi qua, nó sẽ lấy của em nhiều thứ: nước mắt, sự lạc quan, hạnh phúc nhưng cũng trả lại cho em những bài học, sự mạnh mẽ và trưởng thành.\r\n\r\n Thanh xuân là sai lầm, là thương tổn, nhưng em đừng vì thế mà ngần ngại làm những gì mình thích, tỏ tình với người mình yêu. Đừng vì những thất bại của người khác mà không dám bắt đầu, đừng vì những đổ vỡ của người khác mà ngại yêu thương. Đừng để thanh xuân của mình trôi qua trong những cô đơn, sợ hãi. Hãy can đảm để tự viết nên câu chuyện THANH XUÂN của riêng mình.\r\n\r\n “Thanh xuân của chúng ta sẽ kéo dài bao lâu?” – cuốn sách sẽ cuốn trôi những âu lo và sợ hãi về tương lai, cho chúng ta thêm động lực để sống hết mình cho mỗi phút giây hiện tại. Bởi đơn giản, đến cuối cùng, ai chẳng muốn thanh xuân của mình sẽ rực rỡ, tươi đẹp và kéo dài mãi mãi, đúng không?', 57680, 'Sách tiếng Việt', '13 x 20.5', 1, 325, 30, 0, 0),
(4, 1, 'Thương Một Người Đến Đau Lòng', 'Quỳnh Thy', 'NXB Văn Học', 'https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/9/7/9786046999607.jpg', 'Thương Một Người Đến Đau Lòng\r\n\r\nDự kiến phát hành: 14/2/2017\r\n\r\nTuổi trẻ của em vì có anh mà trở nên kì diệu, thanh xuân của em vì có anh mà trở nên tươi đẹp lạ thường.\r\n\r\nTuổi trẻ của chúng ta trôi qua, dù vui hay buồn, đều có những vết xước trong tim. Có người chọn mang theo những nỗi đau ấy, có người chọn can đảm đối mặt, để trái tim yên lành, để những năm tháng về sau không còn xót thương người cũ, chuyện xưa.\r\n\r\nThương một người đến đau lòng là những câu chuyện nhỏ của những người trẻ đã, đang và sẽ đi qua tuổi thanh xuân của mình, đi qua quãng thời gian đáng nhớ nhất trong cuộc đời. Ở đó, cuộc sống của họ như những mảnh ghép đầy màu sắc, là những nỗi niềm riêng, nhưng cùng chung một khát khao yêu thương cháy bỏng. Có thể số phận xô ngã chúng ta, nhưng ta hoàn toàn có thể tiếp tục yêu và sống trọn vẹn những năm tháng ấy.\r\n\r\nSau một tình yêu, là rất nhiều nỗi buồn, quá nhiều tiếc thương và muôn vàn đau đớn. Có những kí ức cần chúng ta phải quên đi, có những hoài niệm cần chúng ra chôn giấu để tiếp tục cuộc hành trình tìm kiếm yêu thương và hạnh phúc của mình. Nhưng chắc chắn, dù có dại ngờ, ngây ngốc vì tình yêu ấy bao nhiêu, chúng ta cũng không bao giờ tiếc nuối hay chối từ tuổi trẻ nhiệt thành dám yêu, dám hận, dám hi sinh và từ bỏ một người.\r\n\r\n“Khi mọi thứ trở về nguyên sơ như thuở chúng mình trẻ dại, như ngày đầu tiên chúng mình nhìn thấy nhau, màu áo anh mặc, mùi hương của anh, tiếng cười của anh, ánh nhìn anh khiến em rung động. Chúng mình nắm tay nhau, nhìn vào mắt nhau như chưa từng chia xa, như chưa từng nói lời tạm biệt. Em thanh thản rồi, anh cũng đừng buồn bã! Nghe không?”\r\n\r\nTuổi thanh xuân của ai cũng vậy, vì một người mà rực rỡ, cũng vì một người mà úa tàn, tuổi thanh xuân dành trọn vẹn để thương một người đến đau lòng…', 80000, 'Sách tiếng việt', '13 x 20.5', 1, 300, 35, 0, 0),
(5, 1, 'Đừng Gọi Anh Là Người Yêu Cũ (Tái Bản 2017)', 'Du Phong', 'NXB Văn Học', 'https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/9/7/9786046951520.jpg', 'Đừng Gọi Anh Là Người Yêu Cũ (Tái Bản 2017)\r\n\r\n“Một số người coi tình cũ như vết thương, một số khác coi như chiến tích, số khác nữa lại coi như nỗi ân hận suốt đời.\r\n\r\nCòn tôi không có tình cũ, vì càng giữ nó càng mới!”\r\n\r\n“Tình cũ” luôn là một thứ gì đó án ngữ trong tim, mà mỗi lần nhớ về lòng ta lại chua xót. Không phải là còn yêu, cũng không phải là hận, để mà không muốn nhận danh xưng Người-Yêu-Cũ, chỉ đơn giản, những thứ cũ kỹ luôn đi cùng cảm giác nuối tiếc buồn thương, mà có ai lại muốn chấp nhận việc trong cuộc đời một người, mình chỉ là kẻ giữ chỗ cho người mới đến sau…\r\n\r\n“Đừng gọi anh là người yêu cũ” là những dòng văn lãng mạn vương vấn chút xót xa về những mối quan hệ không tên, không ràng buộc, tưởng như một cơn gió nhẹ lướt qua cuộc đời, nhưng lại hằn sâu những nỗi niềm khắc khoải khiến người ta không thể dễ dàng quên đi được.\r\n\r\n“Chưa bao giờ nghĩ rằng chúng ta có thể tiến xa đến như vậy…Càng không bao giờ nghĩ rằng chúng ta đã có thể tiến xa đến như vậy, mà vẫn chia tay…”\r\n\r\n“Rồi một ngày cô gái bỏ đi xa\r\n\r\nChàng trai nhớ mà không tìm ra được\r\n\r\nƯớc gì mình trở về ngày tháng trước\r\n\r\nSẽ chẳng bao giờ để tuột tình yêu”\r\n\r\nTừng câu chữ trong cuốn sách được sắp xếp khéo léo với nhiều thể loại khác nhau, khi là những đoạn thơ vần điệu đầy suy tư trăn trở, khi là những bài tản văn nhẹ nhàng đầy lý trí, lúc lại là những truyện ngắn với những ý nghĩa ẩn chứa khiến người đọc phải ngẫm ngợi rất lâu để có thể gật gù: “Thì ra cũng có một người mình từng yêu như vậy!”\r\n\r\nSau 3 năm, nhờ những tình cảm yêu mến mà bạn đọc dành cho tác giả Du Phong và cuốn sách, “Đừng gọi anh là người yêu cũ” được tái bản trong một hình hài khác, với những bài thơ, truyện ngắn cả cũ lẫn mới, hứa hẹn sẽ mang đến cho độc giả thêm nhiều trải nghiệm thú vị và bất ngờ. Một cuốn sách nhất định phải có trong giá sách nhà bạn được ra mắt với phiên bản mới trong mùa hè này!', 80000, 'Sách tiếng việt', '12 x 20', 1, 216, 25, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `uudai`
--

CREATE TABLE `uudai` (
  `idUuDai` int(11) NOT NULL,
  `chiTiet` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `uudai`
--

INSERT INTO `uudai` (`idUuDai`, `chiTiet`) VALUES
(1, 'Đặc biệt giảm giá 10 % cho sinh viên '),
(2, 'Giảm 10 % cho các đơn hàng từ 300.000 trở lên');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baidang`
--
ALTER TABLE `baidang`
  ADD PRIMARY KEY (`idBaiDang`),
  ADD KEY `fk_idKhachHang` (`idKhachHang`);

--
-- Chỉ mục cho bảng `binhluan`
--
ALTER TABLE `binhluan`
  ADD PRIMARY KEY (`idBinhLuan`),
  ADD KEY `fk_idBaiDang1` (`idBaiDang`),
  ADD KEY `fk_idKhacHang1` (`idKhachHang`);

--
-- Chỉ mục cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD KEY `idS` (`idS`);

--
-- Chỉ mục cho bảng `hinh`
--
ALTER TABLE `hinh`
  ADD PRIMARY KEY (`idHinh`),
  ADD KEY `fk_id_bai_dang_hinh` (`idBaiDang`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`idKhachHang`);

--
-- Chỉ mục cho bảng `likebaidang`
--
ALTER TABLE `likebaidang`
  ADD PRIMARY KEY (`idLike`);

--
-- Chỉ mục cho bảng `loaisach`
--
ALTER TABLE `loaisach`
  ADD PRIMARY KEY (`idLS`);

--
-- Chỉ mục cho bảng `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`idMessage`),
  ADD KEY `fk_idKhachHang_message` (`idKhachHang`),
  ADD KEY `fk_id_phong` (`idPhong`);

--
-- Chỉ mục cho bảng `nhom`
--
ALTER TABLE `nhom`
  ADD PRIMARY KEY (`idNhom`),
  ADD KEY `fk_idKhachHang_nhom` (`idKhachHang`),
  ADD KEY `fk_id_user` (`idUser`),
  ADD KEY `fk_id_phong` (`idPhong`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`idS`),
  ADD KEY `fk_id_loai_sach` (`idLS`);

--
-- Chỉ mục cho bảng `uudai`
--
ALTER TABLE `uudai`
  ADD PRIMARY KEY (`idUuDai`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `baidang`
--
ALTER TABLE `baidang`
  MODIFY `idBaiDang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT cho bảng `binhluan`
--
ALTER TABLE `binhluan`
  MODIFY `idBinhLuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT cho bảng `hinh`
--
ALTER TABLE `hinh`
  MODIFY `idHinh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `idKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `loaisach`
--
ALTER TABLE `loaisach`
  MODIFY `idLS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `message`
--
ALTER TABLE `message`
  MODIFY `idMessage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT cho bảng `nhom`
--
ALTER TABLE `nhom`
  MODIFY `idNhom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `idS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `uudai`
--
ALTER TABLE `uudai`
  MODIFY `idUuDai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baidang`
--
ALTER TABLE `baidang`
  ADD CONSTRAINT `fk_idKhachHang` FOREIGN KEY (`idKhachHang`) REFERENCES `khachhang` (`idKhachHang`);

--
-- Các ràng buộc cho bảng `binhluan`
--
ALTER TABLE `binhluan`
  ADD CONSTRAINT `fk_idBaiDang1` FOREIGN KEY (`idBaiDang`) REFERENCES `baidang` (`idBaiDang`),
  ADD CONSTRAINT `fk_idKhacHang1` FOREIGN KEY (`idKhachHang`) REFERENCES `khachhang` (`idKhachHang`);

--
-- Các ràng buộc cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD CONSTRAINT `danhgia_ibfk_1` FOREIGN KEY (`idS`) REFERENCES `sach` (`idS`);

--
-- Các ràng buộc cho bảng `hinh`
--
ALTER TABLE `hinh`
  ADD CONSTRAINT `fk_id_bai_dang_hinh` FOREIGN KEY (`idBaiDang`) REFERENCES `baidang` (`idBaiDang`);

--
-- Các ràng buộc cho bảng `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `fk_idKhachHang_message` FOREIGN KEY (`idKhachHang`) REFERENCES `khachhang` (`idKhachHang`);

--
-- Các ràng buộc cho bảng `nhom`
--
ALTER TABLE `nhom`
  ADD CONSTRAINT `fk_idKhachHang_nhom` FOREIGN KEY (`idKhachHang`) REFERENCES `khachhang` (`idKhachHang`),
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`idUser`) REFERENCES `khachhang` (`idKhachHang`);

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `fk_id_loai_sach` FOREIGN KEY (`idLS`) REFERENCES `loaisach` (`idLS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
