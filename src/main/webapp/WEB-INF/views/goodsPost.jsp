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
</head>
<body>
<h1>Create New Goods</h1>
<div class="container mt-4">
    <form enctype="multipart/form-data" id="goodsForm">
        <label for="gCode">상품코드</label>
        <input type="text" id="gCode" name="gCode" required><br><br>

        <label for="gCategory">카테고리</label>
        <input type="text" id="gCategory" name="gCategory" required><br><br>

        <label for="gName">상품명</label>
        <input type="text" id="gName" name="gName" required><br><br>

        <label for="gContent">내용</label>
        <textarea id="gContent" ></textarea><br><br>

        <label for="gPrice">가격</label>
        <input type="number" id="gPrice" name="gPrice" required><br><br>

        <div class="form-section">
            <div class="form-section-header">대표 이미지 업로드</div>
            <label for="image" class="custom-file-upload">파일 선택</label>
            <input type="file" id="image" multiple style="display: none;" />
            <ul class="image-list" id="file-list"></ul>
        </div>

        <button type="submitBtn">상품등록</button>
    </form>
</div>
<!-- Initialize CKEditor for the 'gContent' field -->
<script>

    $(document).ready(function () {

        var uploadedImages = []; // 업로드한 이미지를 저장할 배열


        $("#goodsForm").on("submit",function(e){
            e.preventDefault();
            const descriptionData = editorInstance.getData();

            const productData = {
                code:$('#gCode').val(),
                category:$('#gCategory').val(),
                name:$('#gName').val(),
                content:descriptionData,
                price:$('#gPrice').val(),
                imageUrls: uploadedImages // 이미지 업로드 로직에서 업데이트된 배열
            };

            $.ajax({
                url:"/api/goods",
                type:"POST",
                contentType:'application/json',
                data:JSON.stringify(productData),
                success:function(response){
                    alert(response.message);
                },
                error:function(xhr,status,error){
                    alert("성공");
                    if(response.errors){
                        alert("실패");
                    }
                }
            });//end ajax
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
                    let data = {

                    }
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
                extraPlugins: [MyCustomUploadAdapterPlugin],
                toolbar: ['undo', 'redo', '|', 'bold', 'italic', '|', 'link', 'bulletedList', 'numberedList', 'blockQuote'],
                // 다른 설정...
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
