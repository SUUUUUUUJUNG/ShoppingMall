<%--
  Created by IntelliJ IDEA.
  User: SHIN SUJUNG
  Date: 2024-04-11
  Time: 오후 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Goods Form</title>
    <!-- CKEditor CDN -->
    <script src="https://cdn.ckeditor.com/ckeditor5/41.0.0/classic/ckeditor.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .table-custom {
            margin-top: 20px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        .header-link {
            margin-bottom: 10px;
            margin-right: 5px;
        }

        .sidebar {
            position: absolute;
            width: 250px;
            height: 100%;
            z-index: 1;
            padding-top: 20px;
        }

        .sidebar .list-group-item {
            border: none;
            background: transparent;
            padding: 15px 20px;
            transition: background-color 0.3s, color 0.3s;
        }

        .sidebar .list-group-item:hover,
        .sidebar .list-group-item:focus {
            background-color: #FFDDEE; /* 호버시 밝은 배경색 변경 */
            color: #6d0230;
            cursor: pointer;
        }

        .sidebar .list-group-item.active {
            font-weight: bold;
            color: white;
            background-color: #D66D75; /* 활성화된 항목에 대한 배경색 */
            text-align: center;
        }

        .sidebar .list-group-item a {
            color: #6d0230;
            text-decoration: none;
        }

        .sidebar .list-group-item a:hover {
            text-decoration: underline;
        }

        .btn-submit {
            margin-top: 20px;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #28a745;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }

    </style>
</head>
<body>
<div class="container mt-4">
    <jsp:include page="../../common/navbar.jsp" flush="true"></jsp:include>
    <h2>상품 추가</h2>
    <hr>
    <div class="sidebar">
        <div class="sidebar">
            <div class="shadow">
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">관리자 페이지</li>
                    <li class="list-group-item"><a href="/admin/goods">상품 관리</a></li>
                    <li class="list-group-item"><a href="/admin/statistics">판매 통계</a></li>
                    <li class="list-group-item">할인 쿠폰(구현예정)</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 메인 컨텐츠 시작 -->
    <div class="col-md-8 offset-md-3 shadow">
        <div class="mb-3 text-end">
        </div>
    <form enctype="multipart/form-data" id="goodsForm">
        <label for="gCode">상품코드</label>
        <input type="text" id="gCode" name="gCode" required><br><br>

        <label for="gCategory">카테고리</label>
        <input type="text" id="gCategory" name="gCategory" required><br><br>

        <label for="gName">상품명</label>
        <input type="text" id="gName" name="gName" required><br><br>

        <label for="gContent">내용</label>
        <textarea id="gContent"></textarea><br><br>

        <label for="gPrice">가격</label>
        <input type="number" id="gPrice" name="gPrice" required><br><br>

        <div class="form-section">
            <div class="form-section-header">대표 이미지 업로드</div>
            <label for="image" class="custom-file-upload">파일 선택</label>
            <input type="file" id="image" multiple style="display: none;"/>
            <ul class="image-list" id="file-list"></ul>
        </div>

        <button type="submit" class="btn-submit">상품등록</button>
    </form>
</div>
<!-- Initialize CKEditor for the 'gContent' field -->
<script>

    $(document).ready(function () {

        var uploadedImages = []; // 업로드한 이미지를 저장할 배열

// 이미지 선택 시 처리 로직
        $("#image").change(function () {
            var files = $('#image')[0].files;
            for (var i = 0; i < files.length; i++) {
                var formData = new FormData();
                formData.append('image', files[i]);

                $.ajax({
                    url: '/api/file',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        var imageUrl = data.imageUrl;
                        if (imageUrl != null && !uploadedImages.includes(imageUrl)) {
                            uploadedImages.push(imageUrl);
                            addHiddenInput(imageUrl);
                            updateImageList();
                        }
                    },
                    error: function (xhr, status, error) {
                        alert("이미지 업로드 실패: " + error);
                    }
                });
            }
        });

        // hidden input 필드 추가 함수
        function addHiddenInput(imageUrl) {
            var input = $('<input>').attr({
                type: 'hidden',
                name: 'imageUrls',
                class: 'imageUrls',
                value: imageUrl
            });
            $('form').append(input);
        }

        // 이미지를 리스트에서 제거하는 함수
        window.removeImage = function (imageUrl) {
            uploadedImages = uploadedImages.filter(function (url) {
                return url !== imageUrl;
            });
            updateImageList();
        }

        // 초기 이미지 리스트 로드
        function loadInitialImageList() {
            updateImageList();
        }

        // 이미지 리스트를 업데이트하는 함수
        function updateImageList() {
            $('#file-list').empty();
            uploadedImages.forEach(function (imageUrl) {
                $('#file-list').append(
                    '<li><img src="/uploaded-images/' + imageUrl + '" style="width: 100px; height: auto;">' +
                    '<button type="button" onclick="removeImage(\'' + imageUrl + '\')">Remove</button></li>'
                );
            });
        }

        loadInitialImageList(); // 페이지 로드 시 초기 이미지 리스트 로드
        let imgSrc = "";
        $("#goodsForm").on("submit", function (e) {
            e.preventDefault();
            const descriptionData = editorInstance.getData();
            const code = $('#gCode').val();
            console.log(code);

            const productData = {
                code: code,
                category: $('#gCategory').val(),
                name: $('#gName').val(),
                content: descriptionData,
                price: $('#gPrice').val(),
                imageUrls: uploadedImages // 이미지 업로드 로직에서 업데이트된 배열
            };

            $.ajax({
                url: "/api/goods",
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify(productData),
                success: function (response) {
                    alert(response.message);
                },
                error: function (xhr, status, error) {

                }
            });//end ajax

            let data = {
                code: code,
                images: uploadedImages,
            }

            $.ajax({
                url: "/api/goods-images",
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (response) {
                    // alert(response.message);
                },
                error: function (xhr, status, error) {
                }
            });//end goods images
        });//end click


        // 어댑터를 CKEditor에 추가
        function MyCustomUploadAdapterPlugin(editor) {
            editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                return new MyUploadAdapter(loader);
            };
        }

        class MyUploadAdapter {
            constructor(loader) {
                this.loader = loader;
            }

            upload() {
                return this.loader.file.then(file => {
                    const formData = new FormData();
                    formData.append('image', file); // 'image' 필드에 파일 추가
                    return fetch('/api/file', {
                        method: 'POST',
                        body: formData,
                    })
                        .then(response => response.json())
                        .then(response => {
                            // 서버 응답에서 이미지 URL을 추출하여 CKEditor 형식에 맞게 반환
                            return {
                                default: '/uploaded-images/' + response.imageUrl // 이미지 URL을 에디터가 요구하는 형식으로 매핑
                            };
                        });
                });
            }
        }

        // 에디터 설정에 플러그인 추가
        let editorInstance;
        ClassicEditor
            .create(document.querySelector('#gContent'), {
                extraPlugins: [MyCustomUploadAdapterPlugin], // 필요한 추가 플러그인 설정
            })
            .then(editor => {
                editorInstance = editor; // 에디터 인스턴스 저장
            })
            .catch(error => {
                console.error(error);
            });

        //응답형 이미지 처리

        // function MyUploadAdapter(loader) {
        //     // 업로더 초기화 코드...
        //     this.loader = loader;
        //     // 이미지를 업로드하고, 서버로부터 응답 받은 후 응답형 이미지 처리를 위한 로직 구현...
        // }


    });
</script>
</body>
</html>
