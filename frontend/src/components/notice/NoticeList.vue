<template>
	<b-container class="page">
		<b-row class="mb-1">
			<b-col class = "text-right">
				<div class="listMenu">
					<!-- <input type="text"><img class="searchImg" src="@/assets/search.png" alt="search.png"> -->
					<b-button v-if="this.userinfo != null && this.userinfo.role == 'A'" variant="primary" class="p-1" @click="moveWrite()"> 글쓰기 </b-button>
				</div>
			</b-col>
		</b-row>
		<b-row>
			<b-col v-if="this.notices.length">
				<b-table-simple hover responsive outlined>
					<b-thead head-variant="light">
						<colgroup>
							<col style="width: 10%" />
							<col style="width: 55%" />
							<col style="width: 15%" />
							<col style="width: 20%" />
						</colgroup>
						<b-tr>
							<b-th>NO</b-th>
							<b-th>제목</b-th>
							<b-th>작성자</b-th>
							<b-th>등록일</b-th>
						</b-tr>
					</b-thead>
					<b-tbody>
						<notice-list-item
							v-for="(notice, index) in this.notices"
							:key="index"
							:cnt="`${index + 1}`"
							:no="notice.no"
							:title="notice.title"
							:writer="notice.userName" 
							:date="notice.date"
							/>
					</b-tbody>
				</b-table-simple>
				<!-- pagenation -->
				<div class="overflow-auto">
						<b-pagination
							v-model= "this.page"
							:total-rows="this.rows"
							:per-page="this.perPage"
							class="justify-content-center"
							@page-click="pageClick"
						></b-pagination>
				</div>
			</b-col>
			<b-col v-else>
				<b-table-simple hover responsive outlined>
					<b-thead head-variant="light">
						<colgroup>
							<col style="width: 10%" />
							<col style="width: 55%" />
							<col style="width: 15%" />
							<col style="width: 20%" />
						</colgroup>
						<b-tr>
							<b-th>NO</b-th>
							<b-th>제목</b-th>
							<b-th>작성자</b-th>
							<b-th>등록일</b-th>
						</b-tr>
					</b-thead>
					<b-tbody>
						<b-td class="text-center" colspan="4" style="padding: 3em;">
							<img src="@/assets/caution.png" alt="caution.png">
							<b>등록된 글이 없습니다.</b>
						</b-td>
					</b-tbody>
				</b-table-simple>
			</b-col>
		</b-row>
		<!-- <notice-pagenation length = "notices.length"/> -->
	</b-container>
</template>

<script>
import NoticeListItem from "@/components/notice/child/NoticeListItem.vue";
// import NoticePagenation from "@/components/notice/child/NoticePagenation.vue";
// import { mapGetters } from "vuex";
import http from "@/util/http-common.js";
import { mapGetters } from 'vuex';

export default {
	components: {
		NoticeListItem,
		// NoticePagenation,
	},
	data(){
		return{
			notices: [],
			rows : 2,
			perPage: 1,
			page: this.$route.params.page,
			
		};
	},
	created() {
		console.log("리스트 created");
		this.getNoticeList();
		// await this.getNoticeList();
		console.log(this.userinfo);
		console.log("리스트 createdEnd");
	},
	mounted() {
		console.log(this.userinfo);
	},
	computed: {
		...mapGetters({userinfo: "accountStore/GET_USER_INFO"})
	},
	methods: {
		getNoticeList(){
			http.get(`/notice`, {params: {page: this.page}})
			.then(response => {
				console.log(response.data);
				this.notices = response.data;
			})
			.catch((error) => {
				console.log(error);
			});
		},
		pageClick: function (button, page){
		this.page = page;
		this.changePage(page);
	},
	changePage(page) {
		this.$router.push({
                name : "NoticeList",
                params: {page: page}
        });
	},
		moveWrite() {
			this.$router.push({ name:"NoticeWrite" });
		},
	},
};
</script>

<style scoped>
.page {
	height: 45em;
}

th, td {
	text-align: center;
}

.listMenu {
	text-align: right;
}

.searchImg {
	width: 1.5em;
	margin: 0.3em;
}
</style>