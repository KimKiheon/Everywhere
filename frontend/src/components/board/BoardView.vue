<template>
    <b-container class="bv-example-row intro-single" id="main">
        <div>
            <div class="row">
                <div class="col-md-12 col-lg-8 w">
                    <div class="title-single-box">
                        <h1 class="title-single">{{ board.title }}</h1>
                    </div>
                </div>
                <div class="col-md-12 col-lg-4">
                    <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="/">Home</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="/">커뮤니티</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="/board/list">여행 정보 게시판</a>
                            </li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div>
                <div class="post-information">
                    <ul class="list-inline text-center color-a d-flex justify-content-end" id="info">
                        <li class="list-inline-item mr-2">
                            <strong>작성자 : </strong>
                            <span class="color-text-a">{{ board.userId }}</span>
                        </li>
                        <li class="list-inline-item mr-2">
                            <strong>작성일 : </strong>
                            <span class="color-text-a">{{ board.registerTime }}</span>
                        </li>
                        <li class="list-inline-item">
                            <strong>조회 : </strong>
                            <span class="color-text-a">{{ board.hit }}</span>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="container">
            <div class="row d-flex ">
                <div class="col-sm-12 d-flex flex-column align-content-center justify-content-center w-75">
                    <div style="height:460px; overflow:auto; border: 1px solid; margin-bottom: 50px;" v-if="plan">
                        <table class="table table-hover">
                            <thead>
                                <tr style="color: #ff4400; font-weight: bolder;">
                                <th>대표이미지</th>
                                <th>관광지명</th>
                                <th>주소</th>
                                </tr>
                            </thead>
                            <tbody id="trip-list">
                                <tr v-for="(attraction, index) in plan" :key="index">
                                    <td><b-img :src="attraction.imgPath"
                                            style="width: 100px; height: 70px" v-if="attraction.imgPath"></b-img>
                                        <b-img :src="attraction.firstImage"
                                            style="width: 100px; height: 70px" v-if="attraction.firstImage"></b-img>
                                    </td>
                                    <td>{{ attraction.title }}</td>
                                    <td>{{ attraction.address1 }} {{ attraction.address2 }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="post-content color-text-a">
                        <pre class="mt-3">
                        {{ board.content }}
                        </pre>
                    </div>
                    <div class="d-flex justify-content-end">
                        <b-button variant="outline-info" size="sm" @click="moveList" class="mr-2">목록</b-button>
                        <div v-if="userInfo != null && userInfo.id == board.userId">
                            <b-button variant="outline-primary" size="sm" @click="moveModifyBoard"
                                class="mr-2">글수정</b-button>
                            <b-button variant="outline-danger" size="sm" @click="deleteBoard">글삭제</b-button>
                        </div>
                    </div>
                    <div class="post-footer">
                        <div class="post-share d-flex justify-content-end">
                            <span>Share: </span>
                            <ul class="list-inline socials">
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-facebook" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-twitter" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-instagram" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-linkedin" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10 w-75">
                    <div class="title-box-d">
                        <h3 class="title-d">Comments</h3>
                    </div>
                    <div class="box-comments">
                        <table class="table table-hover">
                            <colgroup>
                                <col width="90%">
                            </colgroup>
                            <tbody>
                                <div v-for="comment in comments" :key="comment.commentId">
                                    <tr>
                                        <div class="d-flex">
                                            <div class="d-flex flex-column justify-content-center align-items-start">
                                                <div class="d-flex">
                                                    <div
                                                        class="d-flex flex-column justify-content-center align-items-start">
                                                        <span>
                                                            <h4 class="ms-1 mt-0 mb-0">{{ comment.userId }}</h4>
                                                        </span><span class="ms-1 mt-0 mb-0">{{ comment.registerTime
                                                        }}</span>
                                                        <div style="padding: 5px" id="cmt">
                                                            <pre class="ms-1 mt-0 mb-0"
                                                                style="font-size: 20px">{{ comment.content }}</pre>
                                                        </div>
                                                        <div style="padding: 5px" id="cmtUpdate" hidden>
                                                            <textarea id="content_update" class="ms-1 mt-0 mb-0"
                                                                name="content" cols="45"
                                                                style="border: 0; font-family: var(--bs-font-monospace); color: #555555; font-size: 20px;"
                                                                rows="5"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- <div v-if="userInfo.id eq comment.userId">
                                                    <div>
                                                        <button type="button" id="btn-mv-modify-comment"
                                                            class="btn btn-outline-success mb-3 ms-1" hidden>
                                                            댓글수정
                                                        </button>
                                                        <button type="button" id="btn-delete-comment"
                                                            class="btn btn-outline-danger mb-3 ms-1"
                                                            @click="commentDelete(comment.commentId)">
                                                            댓글삭제
                                                        </button>
                                                    </div>
                                                </div> -->
                                                <div v-if="userInfo != null && userInfo.id == comment.userId">
                                                    <button type="button" id="btn-delete-comment"
                                                        class="btn btn-outline-danger mb-3 ms-1"
                                                        @click="commentDelete(comment.commentId)">
                                                        댓글삭제
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </tr>
                                </div>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="form-comments" v-if="userInfo != null">
                <div class="title-box-d">
                    <h3 class="title-d"> Leave a Reply</h3>
                </div>
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <div class="form-group">
                            <input type="hidden" id="userId" value="${userInfo.id}" name="userId">
                            <input type="hidden" id="boardId" value="${board.boardId}" name="boardId">
                            <label for="content">댓글을 입력하세요.</label>
                            <textarea id="content" class="form-control" placeholder="Comment *" name="content" cols="45"
                                rows="8" v-model="comment" required></textarea>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <button type="button" class="btn btn-a" @click="registComment()">댓글 달기</button>
                    </div>
                </div>
            </div>
        </div>
    </b-container>
</template>

<script>
// import moment from "moment";
import { mapState } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
    name: "BoardView",
    data() {
        return {
            board: {},
            comments: {},
            comment: "",
            plan: null,
        };
    },
    created() {
        http.get(`/board/${this.$route.params.boardId}`).then(({ data }) => {
            this.board = data;
            http.get(`/trip/plan/get/${this.board.planId}`).then(({ data }) => {
                if (data.length!=0)
                    this.plan = data;
            })
        });
        http.get(`/board/comment/${this.$route.params.boardId}`).then(({ data }) => {
            this.comments = data;
        });
    },
    computed: {
        message() {
            if (this.board.content) return this.board.content.split("\n").join("<br>");
            return "";
        },
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        moveModifyBoard() {
            this.$router.replace({
                name: "boardmodify",
                params: { boardId: this.board.boardId },
            });
            //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
        },
        deleteBoard() {
            if (confirm("정말로 삭제?")) {
                this.$router.replace({
                    name: "boarddelete",
                    params: { boardId: this.board.boardId },
                });
            }
        },
        moveList() {
            this.$router.push({ name: "boardlist" });
        },
        commentDelete(commentId) {
            if (confirm("정말로 삭제?")) {
                http.delete(`/board/deleteComment/${commentId}`).then(({ data }) => {
                    let msg = "삭제 처리시 문제가 발생했습니다.";
                    if (data === "success") {
                        msg = "삭제가 완료되었습니다.";
                    }
                    alert(msg);
                    this.$router.go();
                });
            }
        },
        registComment() {
            http.post(`/board/writeComment`, {
                userId: this.userInfo.id,
                boardId: this.board.boardId,
                content: this.comment,
            })
                .then(({ data }) => {
                    let msg = "등록 처리시 문제가 발생했습니다.";
                    if (data === "success") {
                        msg = "등록이 완료되었습니다.";
                    }
                    alert(msg);
                    this.$router.go();
                });
        },
    },
    // filters: {
    //   dateFormat(regtime) {
    //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    //   },
    // },
};
</script>

<style>
#main {
    text-align: left;
}

#info {
    text-align: right;
}
</style>